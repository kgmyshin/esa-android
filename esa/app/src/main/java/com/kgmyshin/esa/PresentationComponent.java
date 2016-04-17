/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/17.
 */

package com.kgmyshin.esa;

import com.kgmyshin.esa.fragment.CommentsFragment;
import com.kgmyshin.esa.fragment.LoginFragment;
import com.kgmyshin.esa.fragment.PostFragment;
import com.kgmyshin.esa.fragment.PostsFragment;

public interface PresentationComponent {

    void inject(LoginFragment fragment);

    void inject(CommentsFragment fragment);

    void inject(PostsFragment fragment);

    void inject(PostFragment fragment);

}
