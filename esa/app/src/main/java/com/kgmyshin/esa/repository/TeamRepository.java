/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/03.
 */

package com.kgmyshin.esa.repository;

import com.kgmyshin.esa.data.api.v1.IApiClient;
import com.kgmyshin.esa.data.api.v1.response.TeamResponse;
import com.kgmyshin.esa.dto.Team;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

public class TeamRepository {

    private IApiClient client;

    @Inject
    public TeamRepository(IApiClient client) {
        this.client = client;
    }

    public Observable<Team> findByName(String name) {
        return client.findTeam(name).map(new Func1<TeamResponse, Team>() {
            @Override
            public Team call(TeamResponse teamResponse) {
                return new Team(teamResponse.getName());
            }
        });
    }

}
