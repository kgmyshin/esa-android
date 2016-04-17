/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/09.
 */

package com.kgmyshin.esa.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.kgmyshin.esa.R;
import com.kgmyshin.esa.fragment.SplashFragment;

public class SplashActivity extends AppCompatActivity implements SplashFragment.ScreenTransition {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (savedInstanceState == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            SplashFragment fragment = SplashFragment.newInstance();
            ft.replace(R.id.container, fragment);
            ft.commit();
        }
    }

    @Override
    public void moveToLogin() {
        startActivity(LoginActivity.createIntent(this));
        finish();
    }

    @Override
    public void moveToPosts() {
        startActivity(PostsActivity.createIntent(this));
        finish();
    }
}
