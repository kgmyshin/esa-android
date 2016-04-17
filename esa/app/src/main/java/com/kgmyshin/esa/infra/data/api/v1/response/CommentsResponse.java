/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/02.
 */

package com.kgmyshin.esa.infra.data.api.v1.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class CommentsResponse {

    @SerializedName("comments")
    private List<CommentResponse> comments;
    @SerializedName("prev_page")
    private int prevPage;
    @SerializedName("next_page")
    private int nextPage;
    @SerializedName("total_count")
    private int totalCount;

    public List<CommentResponse> getComments() {
        return comments;
    }

    public int getPrevPage() {
        return prevPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public int getTotalCount() {
        return totalCount;
    }
}
