/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/17.
 */

package com.kgmyshin.esa;

import com.kgmyshin.esa.domain.command.CommandRouter;
import com.kgmyshin.esa.infra.data.DataModule;
import com.kgmyshin.esa.presentation.PresentationComponent;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {DataModule.class, ApplicationModule.class})
public interface EsaComponent extends PresentationComponent {
    CommandRouter router();
}
