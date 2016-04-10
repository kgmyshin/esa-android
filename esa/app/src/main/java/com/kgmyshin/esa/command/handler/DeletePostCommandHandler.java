/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/03.
 */

package com.kgmyshin.esa.command.handler;

import com.kgmyshin.esa.command.DeletePostCommand;
import com.kgmyshin.esa.repository.PostRepository;
import com.kgmyshin.esa.repository.PostRepositoryFactory;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;

public class DeletePostCommandHandler extends CommandHandler<DeletePostCommand> {

    @Inject
    PostRepositoryFactory repositoryFactory;
    @Inject
    @Named("event")
    EventBus eventBus;

    @Override
    public void execute(DeletePostCommand command) {
        PostRepository repository = repositoryFactory.create(command.getTeamName());
        try {
            repository.delete(command.getId());
            eventBus.post(new DeletePostCommand.PostDeletedEvent());
        } catch (IOException e) {
            e.printStackTrace();
            eventBus.post(new DeletePostCommand.FailedDeletePostEvent());
        }
    }
}
