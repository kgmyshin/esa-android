/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/18.
 */

package com.kgmyshin.esa.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

public class NewPostActivity extends AppCompatActivity {

    public static Intent createIntent(Context context) {
        return new Intent(context, NewPostActivity.class);
    }

}
