/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/03.
 */

package com.kgmyshin.esa.command;

public class CreateCommentCommand extends Command {

    private final String teamName;
    private final int postNumber;
    private final String bodyMd;
    private final String user;

    public CreateCommentCommand(String teamName, int postNumber, String bodyMd, String user) {
        this.teamName = teamName;
        this.postNumber = postNumber;
        this.bodyMd = bodyMd;
        this.user = user;
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

    public static class CommentCreatedEvent {

        private final int commentId;

        public CommentCreatedEvent(int commentId) {
            this.commentId = commentId;
        }

        public int getCommentId() {
            return commentId;
        }
    }

    public static class FailedCrateCommentEvent {
    }
}
