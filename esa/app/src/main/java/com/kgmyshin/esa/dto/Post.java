/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/09.
 */

package com.kgmyshin.esa.dto;

import java.util.Date;
import java.util.List;

public class Post {

    private final int number;
    private final String name;
    private final String fullName;
    private final boolean isWip;
    private final String bodyMd;
    private final String bodyHtml;
    private final String message;
    private final List<String> tags;
    private final String category;
    private final int revisionNumber;
    private final String kind;
    private final int numOfComments;
    private final int numOfTasks;
    private final int numOfDoneTasks;
    private final int numOfStargazers;
    private final int numOfWatchers;
    private final boolean hasStar;
    private final boolean hasWatch;

    private final String createdBy;
    private final String updatedBy;
    private final Date createdAt;
    private final Date updatedAt;

    public Post(int number, String name, String fullName, boolean isWip, String bodyMd, String bodyHtml, String message, List<String> tags, String category, int revisionNumber, String kind, int numOfComments, int numOfTasks, int numOfDoneTasks, int numOfStargazers, int numOfWatchers, boolean hasStar, boolean hasWatch, String createdBy, String updatedBy, Date createdAt, Date updatedAt) {
        this.number = number;
        this.name = name;
        this.fullName = fullName;
        this.isWip = isWip;
        this.bodyMd = bodyMd;
        this.bodyHtml = bodyHtml;
        this.message = message;
        this.tags = tags;
        this.category = category;
        this.revisionNumber = revisionNumber;
        this.kind = kind;
        this.numOfComments = numOfComments;
        this.numOfTasks = numOfTasks;
        this.numOfDoneTasks = numOfDoneTasks;
        this.numOfStargazers = numOfStargazers;
        this.numOfWatchers = numOfWatchers;
        this.hasStar = hasStar;
        this.hasWatch = hasWatch;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
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

    public String getCreatedBy() {
        return createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
