/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/09.
 */

package com.kgmyshin.esa.domain.command.handler;

import com.kgmyshin.esa.domain.command.LoginCommand;
import com.kgmyshin.esa.domain.usecase.LoginUseCase;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

public class LoginCommandHandler extends CommandHandler<LoginCommand> {

    private LoginUseCase loginUseCase;
    private EventBus eventBus;

    @Inject
    public LoginCommandHandler(LoginUseCase loginUseCase, EventBus eventBus) {
        this.loginUseCase = loginUseCase;
        this.eventBus = eventBus;
    }

    @Override
    public void execute(LoginCommand command) {
        LoginUseCase.Result result = loginUseCase.login(command.getAccessToken(), command.getTeam());
        if (result == LoginUseCase.Result.SUCCESS) {
            eventBus.postSticky(new LoginCommand.SuccessLoginEvent());
        } else {
            eventBus.postSticky(new LoginCommand.FailedLoginEvent(result.getMessageResId()));
        }
    }
}
