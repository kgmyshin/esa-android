/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/03.
 */

package com.kgmyshin.esa.domain.command;

import java.util.List;

public class CreatePostCommand extends Command {

    private final String teamName;
    private final String name;
    private final String bodyMd;
    private final List<String> tags;
    private final String category;
    private final boolean isWip;
    private final String message;
    private final String user;

    public CreatePostCommand(String teamName, String name, String bodyMd, List<String> tags, String category, boolean isWip, String message, String user) {
        this.teamName = teamName;
        this.name = name;
        this.bodyMd = bodyMd;
        this.tags = tags;
        this.category = category;
        this.isWip = isWip;
        this.message = message;
        this.user = user;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getName() {
        return name;
    }

    public String getBodyMd() {
        return bodyMd;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getCategory() {
        return category;
    }

    public boolean isWip() {
        return isWip;
    }

    public String getMessage() {
        return message;
    }

    public String getUser() {
        return user;
    }

    public static class FailedCreatePostEvent {
    }

    public static class PostCreatedEvent {

        private final int postNumber;

        public PostCreatedEvent(int postNumber) {
            this.postNumber = postNumber;
        }

        public int getPostNumber() {
            return postNumber;
        }
    }
}
