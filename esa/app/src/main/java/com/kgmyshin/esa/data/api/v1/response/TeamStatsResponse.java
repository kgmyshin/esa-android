/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/02.
 */

package com.kgmyshin.esa.data.api.v1.response;

import com.google.gson.annotations.SerializedName;

public final class TeamStatsResponse {
    @SerializedName("members")
    private int numOfMembers;
    @SerializedName("posts")
    private int numOfPosts;
    @SerializedName("comments")
    private int numOfComments;
    @SerializedName("stars")
    private int numOfStars;
    @SerializedName("daily_active_users")
    private int numOfDailyActiveUsers;
    @SerializedName("weekly_active_users")
    private int numOfWeeklyActiveUsers;
    @SerializedName("monthly_active_users")
    private int numOfMonthlyActiveUsers;

    public int getNumOfMembers() {
        return numOfMembers;
    }

    public int getNumOfPosts() {
        return numOfPosts;
    }

    public int getNumOfComments() {
        return numOfComments;
    }

    public int getNumOfStars() {
        return numOfStars;
    }

    public int getNumOfDailyActiveUsers() {
        return numOfDailyActiveUsers;
    }

    public int getNumOfWeeklyActiveUsers() {
        return numOfWeeklyActiveUsers;
    }

    public int getNumOfMonthlyActiveUsers() {
        return numOfMonthlyActiveUsers;
    }
}
