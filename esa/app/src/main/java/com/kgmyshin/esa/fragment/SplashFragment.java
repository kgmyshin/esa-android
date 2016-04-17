/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/17.
 */

package com.kgmyshin.esa.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kgmyshin.esa.Esa;
import com.kgmyshin.esa.presenter.SplashPresenter;

import javax.inject.Inject;

public class SplashFragment extends Fragment {

    private ScreenTransition screenTransition;

    @Inject
    SplashPresenter presenter;

    public static SplashFragment newInstance() {
        return new SplashFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (presenter == null) {
            ((Esa) context.getApplicationContext()).getComponent().inject(this);
            presenter.setFragment(this);
        }
        if (context instanceof ScreenTransition) {
            screenTransition = (ScreenTransition) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        presenter.onCreateView();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void moveToLogin() {
        screenTransition.moveToLogin();
    }

    public void moveToPosts() {
        screenTransition.moveToPosts();
    }

    public interface ScreenTransition {
        void moveToLogin();

        void moveToPosts();
    }

}
