/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/03.
 */

package com.kgmyshin.esa.domain.command;

public class UpdateCommentCommand extends Command {

    private final int id;
    private final String teamName;
    private final int postNumber;
    private final String bodyMd;
    private final String user;

    public UpdateCommentCommand(int id, String teamName, int postNumber, String bodyMd, String user) {
        this.id = id;
        this.teamName = teamName;
        this.postNumber = postNumber;
        this.bodyMd = bodyMd;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getPostNumber() {
        return postNumber;
    }

    public String getBodyMd() {
        return bodyMd;
    }

    public String getUser() {
        return user;
    }

    public static class CommentUpdatedEvent {
    }

    public static class FailedUpdateCommentEvent {
    }
}
