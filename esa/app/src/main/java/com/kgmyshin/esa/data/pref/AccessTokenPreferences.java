/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/02.
 */

package com.kgmyshin.esa.data.pref;

import android.content.Context;
import android.content.SharedPreferences;

public class AccessTokenPreferences {

    // 0f191821f34fe8df360392a3f502610931a3f53ba6d8b4b6fa7555b80e7a9d48

    private static final String PREF_NAME = "access_token";
    private static final String KEY_ACCESS_TOKEN = "access_token";

    private Context context;

    public AccessTokenPreferences(Context context) {
        this.context = context;
    }

    public void putAccessToken(String accessToken) {
        SharedPreferences.Editor editor = getEditor();
        editor.putString(KEY_ACCESS_TOKEN, accessToken);
        editor.commit();
    }

    public String getAccessToken() {
        return getSharedPreferences().getString(KEY_ACCESS_TOKEN, "");
    }

    public void clear() {
        SharedPreferences.Editor editor = getEditor();
        editor.clear();
        editor.commit();
    }

    private SharedPreferences getSharedPreferences() {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    private SharedPreferences.Editor getEditor() {
        return getSharedPreferences().edit();
    }

}
