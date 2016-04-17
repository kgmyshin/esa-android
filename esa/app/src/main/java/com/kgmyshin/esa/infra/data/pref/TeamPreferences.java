package com.kgmyshin.esa.infra.data.pref;

import android.content.Context;
import android.content.SharedPreferences;

public class TeamPreferences {

    private static final String PREF_NAME = "team";
    private static final String KEY_NAME = "name";

    private Context context;

    public TeamPreferences(Context context) {
        this.context = context;
    }

    public void putName(String name) {
        SharedPreferences.Editor editor = getEditor();
        editor.putString(KEY_NAME, name);
        editor.commit();
    }

    public String getName() {
        return getSharedPreferences().getString(KEY_NAME, "");
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
