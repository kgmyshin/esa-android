/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/03.
 */

package com.kgmyshin.esa.domain.command.handler;

import com.kgmyshin.esa.domain.command.DeletePostCommand;
import com.kgmyshin.esa.domain.repository.PostRepository;
import com.kgmyshin.esa.domain.repository.PostRepositoryFactory;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import javax.inject.Inject;

public class DeletePostCommandHandler extends CommandHandler<DeletePostCommand> {

    private PostRepositoryFactory repositoryFactory;
    private EventBus eventBus;

    @Inject
    public DeletePostCommandHandler(PostRepositoryFactory repositoryFactory, EventBus eventBus) {
        this.repositoryFactory = repositoryFactory;
        this.eventBus = eventBus;
    }

    @Override
    public void execute(DeletePostCommand command) {
        PostRepository repository = repositoryFactory.create(command.getTeamName());
        try {
            repository.delete(command.getId());
            eventBus.postSticky(new DeletePostCommand.PostDeletedEvent());
        } catch (IOException e) {
            e.printStackTrace();
            eventBus.postSticky(new DeletePostCommand.FailedDeletePostEvent());
        }
    }
}
