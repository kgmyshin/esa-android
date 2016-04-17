/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/11.
 */

package com.kgmyshin.esa.presentation.presenter;

import com.kgmyshin.esa.presentation.fragment.PostsFragment;
import com.kgmyshin.esa.presentation.viewmodel.PostsViewModel;

import javax.inject.Inject;

public class PostsPresenter {

    private PostsViewModel viewModel;
    private PostsFragment fragment;

    @Inject
    public PostsPresenter(PostsViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void setFragment(PostsFragment fragment) {
        this.fragment = fragment;
    }

    public void onCreateView() {
        fragment.setViewModel(viewModel);
    }

    public void onResume() {
        viewModel.start();
    }

    public void onPause() {
        viewModel.stop();
    }

    public void onScrollToLast() {
        viewModel.loadNextIfNeed();
    }

    public void onClickNewPost() {
        fragment.moveToNewPost();
    }

}
