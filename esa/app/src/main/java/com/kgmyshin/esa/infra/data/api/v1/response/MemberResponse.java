/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/02.
 */

package com.kgmyshin.esa.infra.data.api.v1.response;

import com.google.gson.annotations.SerializedName;

public final class MemberResponse {

    @SerializedName("name")
    private String name;
    @SerializedName("screen_name")
    private String screenName;
    @SerializedName("icon")
    private String icon;
    @SerializedName("email")
    private String email;

    public String getName() {
        return name;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getIcon() {
        return icon;
    }

    public String getEmail() {
        return email;
    }
}
