/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/03.
 */

package com.kgmyshin.esa.infra.data.api.v1;

import com.kgmyshin.esa.infra.data.pref.AccessTokenPreferences;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddAuthorizationInterceptor implements Interceptor {

    AccessTokenPreferences preferences;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder().header("Authorization", preferences.getAccessToken()).build();
        return chain.proceed(request);
    }
}
