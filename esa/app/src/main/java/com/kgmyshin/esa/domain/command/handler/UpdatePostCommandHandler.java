/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/03.
 */

package com.kgmyshin.esa.domain.command.handler;

import com.kgmyshin.esa.domain.command.UpdateCommentCommand;
import com.kgmyshin.esa.domain.command.UpdatePostCommand;
import com.kgmyshin.esa.domain.repository.PostRepository;
import com.kgmyshin.esa.domain.repository.PostRepositoryFactory;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import javax.inject.Inject;

public class UpdatePostCommandHandler extends CommandHandler<UpdatePostCommand> {

    private PostRepositoryFactory repositoryFactory;
    private EventBus eventBus;

    @Inject
    public UpdatePostCommandHandler(PostRepositoryFactory repositoryFactory, EventBus eventBus) {
        this.repositoryFactory = repositoryFactory;
        this.eventBus = eventBus;
    }

    @Override
    public void execute(UpdatePostCommand command) {
        PostRepository repository = repositoryFactory.create(command.getTeamName());
        try {
            repository.update(
                    command.getName(),
                    command.getBodyMd(),
                    command.getTags(),
                    command.getCategory(),
                    command.isWip(),
                    command.getMessage(),
                    command.getCreatedBy(),
                    command.getUpdatedBy()
            );
            eventBus.postSticky(new UpdatePostCommand.PostUpdatedEvent());
        } catch (IOException e) {
            e.printStackTrace();
            eventBus.postSticky(new UpdateCommentCommand.FailedUpdateCommentEvent());
        }
    }
}
