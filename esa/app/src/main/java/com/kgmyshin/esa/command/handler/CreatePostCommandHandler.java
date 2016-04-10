/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/03.
 */

package com.kgmyshin.esa.command.handler;

import com.kgmyshin.esa.command.CreatePostCommand;
import com.kgmyshin.esa.repository.PostRepository;
import com.kgmyshin.esa.repository.PostRepositoryFactory;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;

public class CreatePostCommandHandler extends CommandHandler<CreatePostCommand> {

    @Inject
    PostRepositoryFactory repositoryFactory;
    @Inject
    @Named("event")
    EventBus eventBus;

    @Override
    public void execute(CreatePostCommand command) {
        PostRepository repository = repositoryFactory.create(command.getTeamName());
        try {
            int postNumber = repository.create(
                    command.getName(),
                    command.getBodyMd(),
                    command.getTags(),
                    command.getCategory(),
                    command.isWip(),
                    command.getMessage(),
                    command.getUser()
            );
            eventBus.post(new CreatePostCommand.PostCreatedEvent(postNumber));
        } catch (IOException e) {
            e.printStackTrace();
            eventBus.post(new CreatePostCommand.FailedCreatePostEvent());
        }
    }
}
