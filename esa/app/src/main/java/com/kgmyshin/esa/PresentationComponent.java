/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/17.
 */

package com.kgmyshin.esa;

import com.kgmyshin.esa.fragment.CommentsFragment;
import com.kgmyshin.esa.fragment.LoginFragment;
import com.kgmyshin.esa.fragment.PostFragment;
import com.kgmyshin.esa.fragment.PostsFragment;
import com.kgmyshin.esa.fragment.SplashFragment;

public interface PresentationComponent {

    void inject(SplashFragment fragment);

    void inject(LoginFragment fragment);

    void inject(CommentsFragment fragment);

    void inject(PostsFragment fragment);

    void inject(PostFragment fragment);

}
