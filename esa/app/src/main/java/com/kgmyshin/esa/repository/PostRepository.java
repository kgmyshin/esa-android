/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/03.
 */

package com.kgmyshin.esa.repository;

import com.kgmyshin.esa.data.api.v1.ApiErrorConverter;
import com.kgmyshin.esa.data.api.v1.IApiClient;
import com.kgmyshin.esa.data.api.v1.request.CreatePostRequest;
import com.kgmyshin.esa.data.api.v1.request.UpdatePostRequest;
import com.kgmyshin.esa.data.api.v1.response.PostResponse;
import com.kgmyshin.esa.data.api.v1.response.PostsResponse;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;
import rx.Observable;

public class PostRepository {

    private IApiClient client;
    private String teamName;

    public PostRepository(IApiClient client, String teamName) {
        this.client = client;
        this.teamName = teamName;
    }

    public Observable<PostsResponse> findAll(int page) {
        return client.listPosts(teamName, page);
    }

    public Observable<PostResponse> find(int postNumber) {
        return client.findPost(teamName, postNumber);
    }

    public int create(String name, String bodyMd, List<String> tags, String category, boolean isWip, String message, String user) throws IOException, RuntimeException {
        Response<PostResponse> response = client.createPost(teamName, new CreatePostRequest(name, bodyMd, tags, category, isWip, message, user)).execute();
        if (!response.isSuccessful()) {
            throw ApiErrorConverter.convert(response);
        }
        return response.body().getNumber();
    }

    public void update(String name, String bodyMd, List<String> tags, String category, boolean isWip, String message, String createdBy, String updatedBy) throws IOException, RuntimeException {
        Response response = client.updatePost(teamName, new UpdatePostRequest(name, bodyMd, tags, category, isWip, message, createdBy, updatedBy)).execute();
        if (!response.isSuccessful()) {
            throw ApiErrorConverter.convert(response);
        }
    }

    public void delete(int postNumber) throws IOException, RuntimeException {
        Response response = client.deletePost(teamName, postNumber).execute();
        if (!response.isSuccessful()) {
            throw ApiErrorConverter.convert(response);
        }
    }

}
