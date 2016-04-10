/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/03.
 */

package com.kgmyshin.esa.command;

public class DeleteCommentCommand extends Command {

    private final int id;
    private final String teamName;
    private final int postNumber;

    public DeleteCommentCommand(int id, String teamName, int postNumber) {
        this.id = id;
        this.teamName = teamName;
        this.postNumber = postNumber;
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

    public static class CommentDeletedEvent {
    }

    public static class FailedDeleteCommentEvent {
    }
}
