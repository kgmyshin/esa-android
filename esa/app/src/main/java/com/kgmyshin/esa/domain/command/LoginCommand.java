/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/09.
 */

package com.kgmyshin.esa.domain.command;

public class LoginCommand extends Command {

    private final String accessToken;
    private final String team;

    public LoginCommand(String accessToken, String team) {
        this.accessToken = accessToken;
        this.team = team;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTeam() {
        return team;
    }

    public static class FailedLoginEvent {
        private final int messageResId;

        public FailedLoginEvent(int messageResId) {
            this.messageResId = messageResId;
        }

        public int getMessageResId() {
            return messageResId;
        }
    }

    public static class SuccessLoginEvent {
    }

}
