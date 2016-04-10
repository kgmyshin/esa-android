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

    @Inject
    AccessTokenPreferences preferences;
    @Inject
    IApiClient client;

    public Result login(String accessToken, String teamName) {
        preferences.putAccessToken(accessToken);
        try {
            Response<TeamResponse> response = client.validateTeamName(teamName).execute();
            boolean isSuccessful = response.isSuccessful();
            int code = response.code();
            if (!isSuccessful && response.code() == 404) {
                return Result.FAIL_NOT_FOUND_TEAM;
            } else if (!isSuccessful && response.code() == 403) {
                return Result.FAIL_UNAUTHORIZED;
            } else if (!isSuccessful) {
                return Result.FAIL_OTHER;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Result.FAIL_NETWORK;
        }
        return Result.SUCCESS;
    }

    public enum Result {
        SUCCESS(R.string.login__success),
        FAIL_NOT_FOUND_TEAM(R.string.login__fail_not_found_team),
        FAIL_UNAUTHORIZED(R.string.login__fail_unauthorized),
        FAIL_NETWORK(R.string.login__fail_network),
        FAIL_OTHER(R.string.login__fail_other);
        private final int messageResId;

        Result(int messageResId) {
            this.messageResId = messageResId;
        }

        public int getMssageResId() {
            return messageResId;
        }

        ;
    }


}
