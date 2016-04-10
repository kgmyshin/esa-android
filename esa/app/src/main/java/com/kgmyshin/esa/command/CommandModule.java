/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/09.
 */

package com.kgmyshin.esa.command;

import com.kgmyshin.esa.command.handler.CreateCommentCommandHandler;
import com.kgmyshin.esa.command.handler.CreatePostCommandHandler;
import com.kgmyshin.esa.command.handler.DeleteCommentCommandHandler;
import com.kgmyshin.esa.command.handler.DeletePostCommandHandler;
import com.kgmyshin.esa.command.handler.UpdateCommentCommandHandler;
import com.kgmyshin.esa.command.handler.UpdatePostCommandHandler;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CommandModule {

    @Provides
    @Named("command")
    @Singleton
    public EventBus provideCommandBus() {
        return new EventBus();
    }

    @Provides
    CreateCommentCommandHandler provideCreateCommentCommandHandler() {
        return new CreateCommentCommandHandler();
    }

    @Provides
    CreatePostCommandHandler provideCreatePostCommandHandler() {
        return new CreatePostCommandHandler();
    }

    @Provides
    DeleteCommentCommandHandler provideDeleteCommentCommandHandler() {
        return new DeleteCommentCommandHandler();
    }

    @Provides
    DeletePostCommandHandler provideDeletePostCommandHandler() {
        return new DeletePostCommandHandler();
    }

    @Provides
    UpdateCommentCommandHandler provideUpdateCommentCommandHandler() {
        return new UpdateCommentCommandHandler();
    }

    @Provides
    UpdatePostCommandHandler provideUpdatePostCommandHandler() {
        return new UpdatePostCommandHandler();
    }


}
