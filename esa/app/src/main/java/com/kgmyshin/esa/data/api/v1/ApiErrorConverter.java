/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/09.
 */

package com.kgmyshin.esa.data.api.v1;

import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiErrorConverter {

    public static ApiError convert(Response response) {
        Converter<ResponseBody, Body> converter =
                new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .responseBodyConverter(Body.class, new Annotation[0]);
        try {
            Body body = converter.convert(response.errorBody());
            return new ApiError(response.code(), body.message, body.error);
        } catch (IOException e) {
            e.printStackTrace();
            return new ApiError(response.code(), "", "");
        }
    }

    public static class Body {
        @SerializedName("error")
        private String error;
        @SerializedName("message")
        private String message;

        public Body(String error, String message) {
            this.error = error;
            this.message = message;
        }
    }

}
