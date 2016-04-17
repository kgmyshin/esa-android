/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/03.
 */

package com.kgmyshin.esa.domain.command.handler;

import com.kgmyshin.esa.domain.command.CreateCommentCommand;
import com.kgmyshin.esa.domain.repository.CommentRepository;
import com.kgmyshin.esa.domain.repository.CommentRepositoryFactory;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import javax.inject.Inject;

public class CreateCommentCommandHandler extends CommandHandler<CreateCommentCommand> {

    private CommentRepositoryFactory repositoryFactory;
    private EventBus eventBus;

    @Inject
    public CreateCommentCommandHandler(CommentRepositoryFactory repositoryFactory, EventBus eventBus) {
        this.repositoryFactory = repositoryFactory;
        this.eventBus = eventBus;
    }

    @Override
    public void execute(CreateCommentCommand command) {
        CommentRepository repository = repositoryFactory.create(command.getTeamName(), command.getPostNumber());
        try {
            int commentId = repository.create(command.getBodyMd(), command.getUser());
            eventBus.postSticky(new CreateCommentCommand.CommentCreatedEvent(commentId));
        } catch (IOException e) {
            e.printStackTrace();
            eventBus.postSticky(new CreateCommentCommand.FailedCrateCommentEvent());
        }
    }
}
