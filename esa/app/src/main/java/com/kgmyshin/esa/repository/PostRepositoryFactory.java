/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/03.
 */

package com.kgmyshin.esa.repository;

import com.kgmyshin.esa.data.api.v1.IApiClient;

import javax.inject.Inject;

import dagger.Lazy;

public class PostRepositoryFactory {

    @Inject
    Lazy<IApiClient> apiClient;

    public PostRepository create(String teamName) {
        return new PostRepository(apiClient.get(), teamName);
    }

}
