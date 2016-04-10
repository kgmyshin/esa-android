/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/03.
 */

package com.kgmyshin.esa.command.handler;

import com.kgmyshin.esa.command.UpdateCommentCommand;
import com.kgmyshin.esa.repository.CommentRepository;
import com.kgmyshin.esa.repository.CommentRepositoryFactory;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;

public class UpdateCommentCommandHandler extends CommandHandler<UpdateCommentCommand> {

    @Inject
    CommentRepositoryFactory repositoryFactory;
    @Inject
    @Named("event")
    EventBus eventBus;

    @Override
    public void execute(UpdateCommentCommand command) {
        CommentRepository repository = repositoryFactory.create(command.getTeamName(), command.getPostNumber());
        try {
            repository.update(
                    command.getId(),
                    command.getBodyMd(),
                    command.getUser()
            );
            eventBus.post(new UpdateCommentCommand.CommentUpdatedEvent());
        } catch (IOException e) {
            e.printStackTrace();
            eventBus.post(new UpdateCommentCommand.FailedUpdateCommentEvent());
        }
    }
}
