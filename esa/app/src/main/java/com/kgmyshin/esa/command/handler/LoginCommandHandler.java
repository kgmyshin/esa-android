/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/09.
 */

package com.kgmyshin.esa.command.handler;

import com.kgmyshin.esa.command.LoginCommand;
import com.kgmyshin.esa.usecase.LoginUseCase;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;
import javax.inject.Named;

public class LoginCommandHandler extends CommandHandler<LoginCommand> {

    @Inject
    LoginUseCase loginUseCase;
    @Inject
    @Named("event")
    EventBus eventBus;

    @Override
    public void execute(LoginCommand command) {
        LoginUseCase.Result result = loginUseCase.login(command.getAccessToken(), command.getTeam());
        if (result == LoginUseCase.Result.SUCCESS) {
            eventBus.post(new LoginCommand.SuccessLoginEvent());
        } else {
            eventBus.post(new LoginCommand.FailedLoginEvent(result.getMssageResId()));
        }
    }
}
