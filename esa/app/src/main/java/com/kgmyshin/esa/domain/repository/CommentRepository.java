/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/03.
 */

package com.kgmyshin.esa.domain.repository;

import com.kgmyshin.esa.infra.data.api.v1.ApiErrorConverter;
import com.kgmyshin.esa.infra.data.api.v1.IApiClient;
import com.kgmyshin.esa.infra.data.api.v1.request.CreateCommentRequest;
import com.kgmyshin.esa.infra.data.api.v1.request.UpdateCommentRequest;
import com.kgmyshin.esa.infra.data.api.v1.response.CommentResponse;
import com.kgmyshin.esa.infra.data.api.v1.response.CommentsResponse;
import com.kgmyshin.esa.domain.dto.Comment;
import com.kgmyshin.esa.domain.dto.Page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;
import rx.Single;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class CommentRepository {

    private IApiClient client;
    private String teamName;
    private int postNumber;

    public CommentRepository(IApiClient client, String teamName, int postNumber) {
        this.client = client;
        this.teamName = teamName;
        this.postNumber = postNumber;
    }

    public Single<Page<Comment>> findAll(final int page) {
        return client.listComments(teamName, postNumber, page)
                .map(new Func1<CommentsResponse, Page<Comment>>() {
                    @Override
                    public Page<Comment> call(CommentsResponse response) {
                        List<Comment> comments = new ArrayList<>();
                        for (CommentResponse commentResponse : response.getComments()) {
                            comments.add(convert(commentResponse));
                        }
                        return new Page<>(page, comments, response.getNextPage() > 0, response.getNextPage());
                    }
                })
                .subscribeOn(Schedulers.newThread());
    }

    public Single<Comment> find(int id) {
        return client.findComment(teamName, id)
                .map(new Func1<CommentResponse, Comment>() {
                    @Override
                    public Comment call(CommentResponse response) {
                        return convert(response);
                    }
                })
                .subscribeOn(Schedulers.newThread());
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

    private Comment convert(CommentResponse response) {
        return new Comment(response.getId(), response.getBodyHtml());
    }

}
