/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/10.
 */

package com.kgmyshin.esa.support;

import com.kgmyshin.esa.data.api.v1.ApiErrorConverter;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;

public class RxJavaCallAdapterFactory extends CallAdapter.Factory {

    public static RxJavaCallAdapterFactory create() {
        return new RxJavaCallAdapterFactory();
    }

    @Override
    public CallAdapter<?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        Class<?> rawType = getRawType(returnType);
        if (rawType != Observable.class) {
            return null;
        }
        if (!(returnType instanceof ParameterizedType)) {
            throw new IllegalStateException("Observable return type must be parameterized"
                    + " as Observable<Foo> or Observable<? extends Foo>");
        }

        return getCallAdapter(returnType);
    }

    private CallAdapter<Observable<?>> getCallAdapter(Type returnType) {
        Type observableType = getParameterUpperBound(0, (ParameterizedType) returnType);
        return new SimpleCallAdapter(observableType);
    }

    static final class CallOnSubscribe<T> implements Observable.OnSubscribe<Response<T>> {
        private final Call<T> originalCall;

        CallOnSubscribe(Call<T> originalCall) {
            this.originalCall = originalCall;
        }

        @Override
        public void call(final Subscriber<? super Response<T>> subscriber) {
            // Since Call is a one-shot type, clone it for each new subscriber.
            Call<T> call = originalCall.clone();

            // Wrap the call in a helper which handles both unsubscription and backpressure.
            RequestArbiter<T> requestArbiter = new RequestArbiter<>(call, subscriber);
            subscriber.add(Subscriptions.create(requestArbiter));
            subscriber.setProducer(requestArbiter);
        }
    }

    static final class RequestArbiter<T> extends AtomicBoolean implements Action0, Producer {
        private final Call<T> call;
        private final Subscriber<? super Response<T>> subscriber;

        RequestArbiter(Call<T> call, Subscriber<? super Response<T>> subscriber) {
            this.call = call;
            this.subscriber = subscriber;
        }

        @Override
        public void request(long n) {
            if (n < 0) throw new IllegalArgumentException("n < 0: " + n);
            if (n == 0) return; // Nothing to do when requesting 0.
            if (!compareAndSet(false, true)) return; // Request was already triggered.

            try {
                Response<T> response = call.execute();
                if (!subscriber.isUnsubscribed()) {
                    if (response.isSuccessful()) {
                        subscriber.onNext(response);
                    } else {
                        subscriber.onError(ApiErrorConverter.convert(response));
                    }
                }
            } catch (Throwable t) {
                Exceptions.throwIfFatal(t);
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onError(t);
                }
                return;
            }

            if (!subscriber.isUnsubscribed()) {
                subscriber.onCompleted();
            }
        }

        @Override
        public void call() {
            call.cancel();
        }
    }

    static final class SimpleCallAdapter implements CallAdapter<Observable<?>> {
        private final Type type;

        SimpleCallAdapter(Type type) {
            this.type = type;
        }

        @Override
        public Type responseType() {
            return type;
        }

        @Override
        public <R> Observable<R> adapt(Call<R> call) {
            return Observable.create(new CallOnSubscribe<>(call))
                    .lift(new Observable.Operator<R, Response<R>>() {
                        @Override
                        public Subscriber<? super Response<R>> call(final Subscriber<? super R> child) {
                            return new Subscriber<Response<R>>(child) {
                                @Override
                                public void onCompleted() {
                                    child.onCompleted();
                                }

                                @Override
                                public void onError(Throwable e) {
                                    child.onError(e);
                                }

                                @Override
                                public void onNext(Response<R> response) {
                                    if (response.isSuccessful()) {
                                        child.onNext(response.body());
                                    } else {
                                        child.onError(new HttpException(response));
                                    }
                                }
                            };
                        }
                    });
        }
    }

}
