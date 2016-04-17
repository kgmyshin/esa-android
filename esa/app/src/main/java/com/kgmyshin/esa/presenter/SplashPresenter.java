/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/17.
 */

package com.kgmyshin.esa.presenter;

import com.kgmyshin.esa.fragment.SplashFragment;
import com.kgmyshin.esa.repository.AccessTokenRepository;

import javax.inject.Inject;

public class SplashPresenter {

    private AccessTokenRepository accessTokenRepository;
    private SplashFragment fragment;

    @Inject
    public SplashPresenter(AccessTokenRepository accessTokenRepository) {
        this.accessTokenRepository = accessTokenRepository;
    }

    public void setFragment(SplashFragment fragment) {
        this.fragment = fragment;
    }

    public void onCreateView() {
        if (accessTokenRepository.exsit()) {
            fragment.moveToPosts();
        } else {
            fragment.moveToLogin();
        }
    }

}
