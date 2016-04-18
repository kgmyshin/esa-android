/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/19.
 */

package com.kgmyshin.esa.presentation.presenter;

import com.kgmyshin.esa.domain.command.CreatePostCommand;
import com.kgmyshin.esa.presentation.fragment.NewPostFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

public class NewPostPresenter {

    private NewPostFragment fragment;
    private EventBus eventBus;

    @Inject
    public NewPostPresenter(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void setFragment(NewPostFragment fragment) {
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

    public void onClickSave(String title, String body) {
        fragment.showLoading();
        eventBus.post(new CreatePostCommand(title, body, null, null, true, null, null));
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void receive(CreatePostCommand.PostCreatedEvent event) {
        eventBus.removeStickyEvent(event);
        fragment.dismissLoading();
        fragment.finish();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void receive(CreatePostCommand.FailedCreatePostEvent event) {
        eventBus.removeStickyEvent(event);
        fragment.dismissLoading();
        fragment.showNetworkErrorDialog();
    }
}

