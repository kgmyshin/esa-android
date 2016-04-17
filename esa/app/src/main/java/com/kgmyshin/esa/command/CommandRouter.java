/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/03.
 */

package com.kgmyshin.esa.command;

import com.kgmyshin.esa.command.handler.CreateCommentCommandHandler;
import com.kgmyshin.esa.command.handler.CreatePostCommandHandler;
import com.kgmyshin.esa.command.handler.DeleteCommentCommandHandler;
import com.kgmyshin.esa.command.handler.DeletePostCommandHandler;
import com.kgmyshin.esa.command.handler.LoginCommandHandler;
import com.kgmyshin.esa.command.handler.UpdateCommentCommandHandler;
import com.kgmyshin.esa.command.handler.UpdatePostCommandHandler;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

public class CommandRouter {

    private EventBus eventBus;
    private CreateCommentCommandHandler createCommentCommandHandler;
    private CreatePostCommandHandler createPostCommandHandler;
    private DeleteCommentCommandHandler deleteCommentCommandHandler;
    private DeletePostCommandHandler deletePostCommandHandler;
    private UpdateCommentCommandHandler updateCommentCommandHandler;
    private UpdatePostCommandHandler updatePostCommandHandler;
    private LoginCommandHandler loginCommandHandler;

    @Inject
    public CommandRouter(EventBus eventBus, CreateCommentCommandHandler createCommentCommandHandler, CreatePostCommandHandler createPostCommandHandler, DeleteCommentCommandHandler deleteCommentCommandHandler, DeletePostCommandHandler deletePostCommandHandler, UpdateCommentCommandHandler updateCommentCommandHandler, UpdatePostCommandHandler updatePostCommandHandler, LoginCommandHandler loginCommandHandler) {
        this.eventBus = eventBus;
        this.createCommentCommandHandler = createCommentCommandHandler;
        this.createPostCommandHandler = createPostCommandHandler;
        this.deleteCommentCommandHandler = deleteCommentCommandHandler;
        this.deletePostCommandHandler = deletePostCommandHandler;
        this.updateCommentCommandHandler = updateCommentCommandHandler;
        this.updatePostCommandHandler = updatePostCommandHandler;
        this.loginCommandHandler = loginCommandHandler;
    }

    public void start() {
        eventBus.register(this);
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

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void receive(LoginCommand command) {
        loginCommandHandler.execute(command);
    }

}
