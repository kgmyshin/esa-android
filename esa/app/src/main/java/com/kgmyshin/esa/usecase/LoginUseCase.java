/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/09.
 */

package com.kgmyshin.esa.usecase;

import com.kgmyshin.esa.R;
import com.kgmyshin.esa.data.api.v1.IApiClient;
import com.kgmyshin.esa.data.api.v1.response.TeamResponse;
import com.kgmyshin.esa.data.pref.AccessTokenPreferences;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Response;

public class LoginUseCase {

    private AccessTokenPreferences preferences;
    private IApiClient client;

    @Inject
    public LoginUseCase(AccessTokenPreferences preferences, IApiClient client) {
        this.preferences = preferences;
        this.client = client;
    }

    public Result login(String accessToken, String teamName) {

        if (!validateAccessToken(accessToken)) {
            return Result.FAIL_EMPTY_ACCESS_TOKEN;
        }
        if (!validateTeamName(teamName)) {
            return Result.FAIL_EMPTY_TEAM_NAME;
        }

        preferences.putAccessToken(accessToken);
        Result result;
        try {
            Response<TeamResponse> response = client.validateTeamName(teamName).execute();
            boolean isSuccessful = response.isSuccessful();
            int code = response.code();
            if (!isSuccessful && code == 404) {
                result = Result.FAIL_NOT_FOUND_TEAM;
            } else if (!isSuccessful && code == 401) {
                result = Result.FAIL_UNAUTHORIZED;
            } else if (!isSuccessful) {
                result = Result.FAIL_OTHER;
            } else {
                result = Result.SUCCESS;
            }
        } catch (IOException e) {
            e.printStackTrace();
            result = Result.FAIL_NETWORK;
        }
        if (result != Result.SUCCESS) {
            preferences.clear();
        }
        return result;
    }

    private boolean validateAccessToken(String accessToken) {
        return accessToken != null && !accessToken.isEmpty();
    }

    private boolean validateTeamName(String teamName) {
        return teamName != null && !teamName.isEmpty();
    }

    public enum Result {
        SUCCESS(R.string.login__success),
        FAIL_EMPTY_ACCESS_TOKEN(R.string.login__fail_empty_access_token),
        FAIL_EMPTY_TEAM_NAME(R.string.login__fail_empty_team_name),
        FAIL_NOT_FOUND_TEAM(R.string.login__fail_not_found_team),
        FAIL_UNAUTHORIZED(R.string.login__fail_unauthorized),
        FAIL_NETWORK(R.string.login__fail_network),
        FAIL_OTHER(R.string.login__fail_other);
        private final int messageResId;

        Result(int messageResId) {
            this.messageResId = messageResId;
        }

        public int getMessageResId() {
            return messageResId;
        }

        ;
    }


}
