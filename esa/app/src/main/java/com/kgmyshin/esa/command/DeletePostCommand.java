/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/03.
 */

package com.kgmyshin.esa.command;

public class DeletePostCommand extends Command {

    private final String teamName;
    private final int id;

    public DeletePostCommand(String teamName, int id) {
        this.teamName = teamName;
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getId() {
        return id;
    }

    public static class FailedDeletePostEvent {
    }

    public static class PostDeletedEvent {
    }
}
