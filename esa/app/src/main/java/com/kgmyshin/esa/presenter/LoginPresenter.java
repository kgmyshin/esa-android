/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/11.
 */

package com.kgmyshin.esa.presenter;

import android.widget.Toast;

import com.kgmyshin.esa.command.LoginCommand;
import com.kgmyshin.esa.fragment.LoginFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

public class LoginPresenter {

    private LoginFragment fragment;

    private EventBus eventBus;

    @Inject
    public LoginPresenter(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void setFragment(LoginFragment fragment) {
        this.fragment = fragment;
    }

    public void onResume() {
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }
    }

    public void onPause() {
        if (eventBus.isRegistered(this)) {
            eventBus.unregister(this);
        }
    }

    public void onClickLogin(String accessToken, String teamName) {
        fragment.showLoading();
        eventBus.post(new LoginCommand(accessToken, teamName));
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void receive(LoginCommand.SuccessLoginEvent event) {
        eventBus.removeStickyEvent(event);
        fragment.dismissLoading();
        Toast.makeText(fragment.getContext(), "成功", Toast.LENGTH_SHORT).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void receive(LoginCommand.FailedLoginEvent event) {
        eventBus.removeStickyEvent(event);
        fragment.dismissLoading();
        fragment.showErrorDialog(event.getMessageResId());
    }

}
