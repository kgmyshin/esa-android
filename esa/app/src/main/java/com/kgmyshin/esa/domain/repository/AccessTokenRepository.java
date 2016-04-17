/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/17.
 */

package com.kgmyshin.esa.domain.repository;

import com.kgmyshin.esa.infra.data.pref.AccessTokenPreferences;

import javax.inject.Inject;

public class AccessTokenRepository {

    private AccessTokenPreferences pref;

    @Inject
    public AccessTokenRepository(AccessTokenPreferences pref) {
        this.pref = pref;
    }

    public boolean exist() {
        String accessToken = pref.getAccessToken();
        return accessToken != null && !accessToken.isEmpty();
    }

    public void save(String accessToken) {
        pref.putAccessToken(accessToken);
    }

    public String find() {
        return pref.getAccessToken();
    }

    public void delete() {
        pref.clear();
    }

}
