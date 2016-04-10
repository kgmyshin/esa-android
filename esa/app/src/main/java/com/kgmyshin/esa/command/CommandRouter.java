/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/03.
 */

package com.kgmyshin.esa.command;

import com.kgmyshin.esa.command.handler.CreateCommentCommandHandler;
import com.kgmyshin.esa.command.handler.CreatePostCommandHandler;
import com.kgmyshin.esa.command.handler.DeleteCommentCommandHandler;
import com.kgmyshin.esa.command.handler.DeletePostCommandHandler;
import com.kgmyshin.esa.command.handler.UpdateCommentCommandHandler;
import com.kgmyshin.esa.command.handler.UpdatePostCommandHandler;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;
import javax.inject.Named;

public class CommandRouter {

    @Inject
    @Named("command")
    EventBus commandBus;

    @Inject
    CreateCommentCommandHandler createCommentCommandHandler;
    @Inject
    CreatePostCommandHandler createPostCommandHandler;
    @Inject
    DeleteCommentCommandHandler deleteCommentCommandHandler;
    @Inject
    DeletePostCommandHandler deletePostCommandHandler;
    @Inject
    UpdateCommentCommandHandler updateCommentCommandHandler;
    @Inject
    UpdatePostCommandHandler updatePostCommandHandler;

    public void start() {
        commandBus.register(this);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void receive(CreateCommentCommand command) {
        createCommentCommandHandler.execute(command);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void receive(CreatePostCommand command) {
        createPostCommandHandler.execute(command);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void receive(DeleteCommentCommand command) {
        deleteCommentCommandHandler.execute(command);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void receive(DeletePostCommand command) {
        deletePostCommandHandler.execute(command);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void receive(UpdateCommentCommand command) {
        updateCommentCommandHandler.execute(command);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void receive(UpdatePostCommand command) {
        updatePostCommandHandler.execute(command);
    }

}
