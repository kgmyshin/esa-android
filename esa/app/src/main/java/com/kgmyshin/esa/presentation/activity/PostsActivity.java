/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/09.
 */

package com.kgmyshin.esa.presentation.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.kgmyshin.esa.R;
import com.kgmyshin.esa.presentation.fragment.PostsFragment;

public class PostsActivity extends AppCompatActivity implements PostsFragment.ScreenTransition {

    public static Intent createIntent(Context context) {
        return new Intent(context, PostsActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (savedInstanceState == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            PostsFragment fragment = PostsFragment.newInstance();
            ft.replace(R.id.container, fragment);
            ft.commit();
        }
    }

    @Override
    public void moveToNewPost() {
        startActivity(NewPostActivity.createIntent(this));
    }
}
