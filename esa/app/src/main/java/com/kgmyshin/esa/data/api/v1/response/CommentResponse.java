/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/02.
 */

package com.kgmyshin.esa.data.api.v1.response;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public final class CommentResponse {
    @SerializedName("id")
    private int id;
    @SerializedName("body_md")
    private String bodyMd;
    @SerializedName("body_html")
    private String bodyHtml;
    @SerializedName("url")
    private String url;
    @SerializedName("created_by")
    private MemberResponse createdBy;
    @SerializedName("created_at")
    private Date cratedAt;
    @SerializedName("updated_at")
    private Date updatedAt;

    public int getId() {
        return id;
    }

    public String getBodyMd() {
        return bodyMd;
    }

    public String getBodyHtml() {
        return bodyHtml;
    }

    public String getUrl() {
        return url;
    }

    public MemberResponse getCreatedBy() {
        return createdBy;
    }

    public Date getCratedAt() {
        return cratedAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
