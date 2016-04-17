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
import com.kgmyshin.esa.dto.Page;
import com.kgmyshin.esa.dto.Post;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;
import rx.Single;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class PostRepository {

    private IApiClient client;
    private String teamName;

    public PostRepository(IApiClient client, String teamName) {
        this.client = client;
        this.teamName = teamName;
    }

    public Single<Page<Post>> findAll(final int page) {
        return client.listPosts(teamName, page)
                .map(new Func1<PostsResponse, Page<Post>>() {
                    @Override
                    public Page<Post> call(PostsResponse response) {
                        List<Post> posts = new ArrayList<>();
                        for (PostResponse postResponse : response.getPosts()) {
                            posts.add(convert(postResponse));
                        }
                        return new Page<>(page, posts, response.getNextPage() > 0, response.getNextPage());
                    }
                })
                .subscribeOn(Schedulers.newThread());
    }

    public Single<Post> find(int postNumber) {
        return client.findPost(teamName, postNumber).map(new Func1<PostResponse, Post>() {
            @Override
            public Post call(PostResponse response) {
                return convert(response);
            }
        }).subscribeOn(Schedulers.newThread());
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

    private Post convert(PostResponse response) {
        return new Post(
                response.getNumber(),
                response.getName(),
                response.getFullName(),
                response.isWip(),
                response.getBodyMd(),
                response.getBodyHtml(),
                response.getMessage(),
                response.getTags(),
                response.getCategory(),
                response.getRevisionNumber(),
                response.getKind(),
                response.getNumOfComments(),
                response.getNumOfTasks(),
                response.getNumOfDoneTasks(),
                response.getNumOfStargazers(),
                response.getNumOfWatchers(),
                response.isHasStar(),
                response.isHasWatch(),
                response.getCreatedBy().getName(),
                response.getUpdatedBy().getName(),
                response.getCreatedAt(),
                response.getUpdatedAt()
        );
    }

}
