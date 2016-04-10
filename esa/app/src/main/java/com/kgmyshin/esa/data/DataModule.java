/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/03.
 */

package com.kgmyshin.esa.data;

import com.kgmyshin.esa.data.api.v1.ApiClientFactory;
import com.kgmyshin.esa.data.api.v1.IApiClient;
import com.kgmyshin.esa.data.api.v1.IApiObservableClient;
import com.kgmyshin.esa.data.pref.AccessTokenPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class DataModule {

    @Singleton
    @Provides
    public AccessTokenPreferences provideAccessTokenPreferences(AccessTokenPreferences preferences) {
        return preferences;
    }

    @Singleton
    @Provides
    public ApiClientFactory provideApiClientFactory(ApiClientFactory factory) {
        return factory;
    }

    @Singleton
    @Provides
    public IApiClient provideApiClient(ApiClientFactory factory) {
        return factory.createClient();
    }

    @Singleton
    @Provides
    public IApiObservableClient provideApiObservableClient(ApiClientFactory factory) {
        return factory.createObservableClient();
    }

}