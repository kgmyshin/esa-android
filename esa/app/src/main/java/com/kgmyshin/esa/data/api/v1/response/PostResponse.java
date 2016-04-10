/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/02.
 */

package com.kgmyshin.esa.data.api.v1.response;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public final class PostResponse {

    @SerializedName("number")
    private int number;
    @SerializedName("name")
    private int name;
    @SerializedName("full_name")
    private String fullName;
    @SerializedName("wip")
    private boolean isWip;
    @SerializedName("body_md")
    private String bodyMd;
    @SerializedName("body_html")
    private String bodyHtml;
    @SerializedName("message")
    private String message;
    @SerializedName("url")
    private String url;
    @SerializedName("tags")
    private List<String> tags;
    @SerializedName("category")
    private String category;
    @SerializedName("revision_number")
    private int revisionNumber;

    @SerializedName("kind")
    private String kind;
    @SerializedName("comments_count")
    private int numOfComments;
    @SerializedName("tasks_count")
    private int numOfTasks;
    @SerializedName("done_tasks_count")
    private int numOfDoneTasks;
    @SerializedName("stargazers_count")
    private int numOfStargazers;
    @SerializedName("watchers_count")
    private int numOfWatchers;
    @SerializedName("star")
    private boolean hasStar;
    @SerializedName("watch")
    private boolean hasWatch;

    @SerializedName("created_by")
    private MemberResponse createdBy;
    @SerializedName("updated_by")
    private MemberResponse updatedBy;
    @SerializedName("created_at")
    private Date createdAt;
    @SerializedName("updated_at")
    private Date updatedAt;

    public int getNumber() {
        return number;
    }

    public int getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public boolean isWip() {
        return isWip;
    }

    public String getBodyMd() {
        return bodyMd;
    }

    public String getBodyHtml() {
        return bodyHtml;
    }

    public String getMessage() {
        return message;
    }

    public String getUrl() {
        return url;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getCategory() {
        return category;
    }

    public int getRevisionNumber() {
        return revisionNumber;
    }

    public String getKind() {
        return kind;
    }

    public int getNumOfComments() {
        return numOfComments;
    }

    public int getNumOfTasks() {
        return numOfTasks;
    }

    public int getNumOfDoneTasks() {
        return numOfDoneTasks;
    }

    public int getNumOfStargazers() {
        return numOfStargazers;
    }

    public int getNumOfWatchers() {
        return numOfWatchers;
    }

    public boolean isHasStar() {
        return hasStar;
    }

    public boolean isHasWatch() {
        return hasWatch;
    }

    public MemberResponse getCreatedBy() {
        return createdBy;
    }

    public MemberResponse getUpdatedBy() {
        return updatedBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
