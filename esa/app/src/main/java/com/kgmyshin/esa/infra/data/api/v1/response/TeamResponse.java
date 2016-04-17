/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/02.
 */

package com.kgmyshin.esa.infra.data.api.v1.response;

import com.google.gson.annotations.SerializedName;

public final class TeamResponse {

    @SerializedName("name")
    private String name;
    @SerializedName("privacy")
    private String privacy;
    @SerializedName("description")
    private String description;
    @SerializedName("icon")
    private String icon;
    @SerializedName("url")
    private String url;

    public String getName() {
        return name;
    }

    public String getPrivacy() {
        return privacy;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

    public String getUrl() {
        return url;
    }
}
