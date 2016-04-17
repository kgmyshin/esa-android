/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/02.
 */

package com.kgmyshin.esa.infra.data.api.v1.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class UpdatePostRequest {

    @SerializedName("name")
    private final String name;
    @SerializedName("body_md")
    private final String bodyMd;
    @SerializedName("tags")
    private final List<String> tags;
    @SerializedName("category")
    private final String category;
    @SerializedName("wip")
    private final boolean isWip;
    @SerializedName("message")
    private final String message;
    @SerializedName("created_by")
    private final String createdBy;
    @SerializedName("updated_by")
    private final String updatedBy;

    public UpdatePostRequest(String name, String bodyMd, List<String> tags, String category, boolean isWip, String message, String createdBy, String updatedBy) {
        this.name = name;
        this.bodyMd = bodyMd;
        this.tags = tags;
        this.category = category;
        this.isWip = isWip;
        this.message = message;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }

    public String getName() {
        return name;
    }

    public String getBodyMd() {
        return bodyMd;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getCategory() {
        return category;
    }

    public boolean isWip() {
        return isWip;
    }

    public String getMessage() {
        return message;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }
}
