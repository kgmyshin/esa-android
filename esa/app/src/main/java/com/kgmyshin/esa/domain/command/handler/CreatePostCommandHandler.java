/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/03.
 */

package com.kgmyshin.esa.domain.command.handler;

import com.kgmyshin.esa.domain.command.CreatePostCommand;
import com.kgmyshin.esa.domain.repository.PostRepository;
import com.kgmyshin.esa.domain.repository.PostRepositoryFactory;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import javax.inject.Inject;

public class CreatePostCommandHandler extends CommandHandler<CreatePostCommand> {

    private PostRepositoryFactory repositoryFactory;
    private EventBus eventBus;

    @Inject
    public CreatePostCommandHandler(PostRepositoryFactory repositoryFactory, EventBus eventBus) {
        this.repositoryFactory = repositoryFactory;
        this.eventBus = eventBus;
    }

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
            eventBus.postSticky(new CreatePostCommand.PostCreatedEvent(postNumber));
        } catch (IOException e) {
            e.printStackTrace();
            eventBus.postSticky(new CreatePostCommand.FailedCreatePostEvent());
        }
    }
}
