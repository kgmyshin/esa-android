/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/17.
 */

package com.kgmyshin.esa;

import android.app.Application;

import com.kgmyshin.esa.infra.data.DataModule;

public class Esa extends Application {

    private EsaComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerEsaComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .dataModule(new DataModule())
                .build();
        component.router().start();
    }

    public EsaComponent getComponent() {
        return component;
    }
}
