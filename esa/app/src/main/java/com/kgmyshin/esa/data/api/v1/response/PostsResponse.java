/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/02.
 */

package com.kgmyshin.esa.data.api.v1.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class PostsResponse {

    @SerializedName("posts")
    private List<PostResponse> posts;
    @SerializedName("prev_page")
    private int prevPage;
    @SerializedName("next_page")
    private int nextPage;
    @SerializedName("total_count")
    private int totalCount;

    public List<PostResponse> getPosts() {
        return posts;
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

