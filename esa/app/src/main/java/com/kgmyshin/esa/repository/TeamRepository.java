/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/03.
 */

package com.kgmyshin.esa.repository;

import com.kgmyshin.esa.data.pref.TeamPreferences;

import javax.inject.Inject;

public class TeamRepository {

    private TeamPreferences teamPreferences;

    @Inject
    public TeamRepository(TeamPreferences teamPreferences) {
        this.teamPreferences = teamPreferences;
    }

    public void save(String name) {
        teamPreferences.putName(name);
    }

    public String find() {
        return teamPreferences.getName();
    }

}
