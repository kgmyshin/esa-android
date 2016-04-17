/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/02.
 */

package com.kgmyshin.esa.infra.data.api.v1;

import com.kgmyshin.esa.infra.data.api.v1.request.CreateCommentRequest;
import com.kgmyshin.esa.infra.data.api.v1.request.CreatePostRequest;
import com.kgmyshin.esa.infra.data.api.v1.request.UpdateCommentRequest;
import com.kgmyshin.esa.infra.data.api.v1.request.UpdatePostRequest;
import com.kgmyshin.esa.infra.data.api.v1.response.CommentResponse;
import com.kgmyshin.esa.infra.data.api.v1.response.CommentsResponse;
import com.kgmyshin.esa.infra.data.api.v1.response.MembersResponse;
import com.kgmyshin.esa.infra.data.api.v1.response.PostResponse;
import com.kgmyshin.esa.infra.data.api.v1.response.PostsResponse;
import com.kgmyshin.esa.infra.data.api.v1.response.TeamResponse;
import com.kgmyshin.esa.infra.data.api.v1.response.TeamStatsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Single;

public interface IApiClient {

    @GET("/v1/teams/{team_name}")
    Call<TeamResponse> validateTeamName(@Path("team_name") String teamName);

    @GET("/v1/teams?per_page=100")
    Single<Response<List<TeamResponse>>> listTeams(@Query("page") int page);

    @GET("/v1/teams/{team_name}")
    Single<TeamResponse> findTeam(@Path("team_name") String teamName);

    @GET("/v1/teams/{team_name}/stats")
    Single<TeamStatsResponse> findTeamStats(@Path("team_name") String teamName);

    @GET("/v1/teams/{team_name}/members?per_page=100")
    Single<MembersResponse> listMembers(@Path("team_name") String teamName, @Query("page") int page);

    @GET("/v1/teams/{team_name}/posts?per_page=100")
    Single<PostsResponse> listPosts(@Path("team_name") String teamName, @Query("page") int page);

    @GET("/v1/teams/{team_name}/posts/{post_number}")
    Single<PostResponse> findPost(@Path("team_name") String teamName, @Path("post_number") int postNumber);

    @POST("/v1/teams/{team_name}/posts")
    Call<PostResponse> createPost(@Path("team_name") String teamName, @Body CreatePostRequest request);

    @PATCH("/v1/teams/{team_name}/posts/{post_number}")
    Call<PostResponse> updatePost(@Path("team_name") String teamName, @Body UpdatePostRequest request);

    @DELETE("/v1/teams/{team_name}/posts/{post_number}")
    Call<Void> deletePost(@Path("team_name") String teamName, @Path("post_number") int postNumber);

    @GET("/v1/teams/{team_name}/posts/{post_number}/comments")
    Single<CommentsResponse> listComments(@Path("team_name") String teamName, @Path("post_number") int postNumber, @Query("page") int page);

    @GET("/v1/teams/{team_name}/comments/{comment_id}")
    Single<CommentResponse> findComment(@Path("team_name") String teamName, @Path("comment_id") int commentId);

    @POST("/v1/teams/{team_name}/posts/{post_number}/comments")
    Call<CommentResponse> createComment(@Path("team_name") String teamName, @Path("post_number") int postNumber, @Body CreateCommentRequest request);

    @PATCH("/v1/teams/{team_name}/comments/{comment_id}")
    Call<CommentResponse> updateComment(@Path("team_name") String teamName, @Path("comment_id") int commentId, @Body UpdateCommentRequest request);

    @DELETE("/v1/teams/{team_name}/comments/{comment_id}")
    Call<Void> deleteComment(@Path("team_name") String teamName, @Path("comment_id") int commentId);
}
