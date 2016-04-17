/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/03.
 */

package com.kgmyshin.esa.repository;

import com.kgmyshin.esa.data.api.v1.IApiClient;

import javax.inject.Inject;

import dagger.Lazy;

public class CommentRepositoryFactory {

    private Lazy<IApiClient> apiClient;

    @Inject
    public CommentRepositoryFactory(Lazy<IApiClient> apiClient) {
        this.apiClient = apiClient;
    }

    public CommentRepository create(String teamName, int postNumber) {
        return new CommentRepository(apiClient.get(), teamName, postNumber);
    }

}
