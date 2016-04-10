/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/03.
 */

package com.kgmyshin.esa.repository;

import com.kgmyshin.esa.data.api.v1.ApiErrorConverter;
import com.kgmyshin.esa.data.api.v1.IApiClient;
import com.kgmyshin.esa.data.api.v1.request.CreateCommentRequest;
import com.kgmyshin.esa.data.api.v1.request.UpdateCommentRequest;
import com.kgmyshin.esa.data.api.v1.response.CommentResponse;
import com.kgmyshin.esa.data.api.v1.response.CommentsResponse;

import java.io.IOException;

import retrofit2.Response;
import rx.Observable;

public class CommentRepository {

    private IApiClient client;
    private String teamName;
    private int postNumber;

    public CommentRepository(IApiClient client, String teamName, int postNumber) {
        this.client = client;
        this.teamName = teamName;
        this.postNumber = postNumber;
    }

    public Observable<CommentsResponse> findAll(int page) {
        return client.listComments(teamName, postNumber, page);
    }

    public Observable<CommentResponse> find(int id) {
        return client.findComment(teamName, id);
    }

    public int create(String bodyMd, String user) throws IOException, RuntimeException {
        Response<CommentResponse> response = client.createComment(teamName, postNumber, new CreateCommentRequest(bodyMd, user)).execute();
        if (!response.isSuccessful()) {
            throw ApiErrorConverter.convert(response);
        }
        return response.body().getId();
    }

    public void update(int id, String bodyMd, String user) throws IOException, RuntimeException {
        Response response = client.updateComment(teamName, id, new UpdateCommentRequest(bodyMd, user)).execute();
        if (!response.isSuccessful()) {
            throw ApiErrorConverter.convert(response);
        }
    }

    public void delete(int id) throws IOException, RuntimeException {
        Response response = client.deleteComment(teamName, id).execute();
        if (!response.isSuccessful()) {
            throw ApiErrorConverter.convert(response);
        }
    }


}
