/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/09.
 */

package com.kgmyshin.esa.presentation.fragment;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.kgmyshin.esa.Esa;
import com.kgmyshin.esa.databinding.FragmentPostsBinding;
import com.kgmyshin.esa.domain.dto.Post;
import com.kgmyshin.esa.presentation.presenter.PostsPresenter;
import com.kgmyshin.esa.presentation.viewmodel.PostsViewModel;

import java.util.List;

import javax.inject.Inject;

public class PostsFragment extends Fragment {

    private FragmentPostsBinding binding;
    private ScreenTransition screenTransition;

    @Inject
    PostsPresenter presenter;

    public static PostsFragment newInstance() {
        return new PostsFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (presenter == null) {
            ((Esa) context.getApplicationContext()).getComponent().inject(this);
            presenter.setFragment(this);
        }
        if (context instanceof ScreenTransition) {
            screenTransition = (ScreenTransition) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPostsBinding.inflate(inflater, container, false);
        binding.postListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (totalItemCount == firstVisibleItem + visibleItemCount) {
                    presenter.onScrollToLast();
                }
            }
        });
        binding.newPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClickNewPost();
            }
        });

        presenter.onCreateView();
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onStart();
        presenter.onResume();
    }

    @Override
    public void onPause() {
        presenter.onPause();
        super.onStop();
    }

    public void setViewModel(PostsViewModel viewModel) {
        binding.setViewModel(viewModel);
    }

    public void moveToNewPost() {
        screenTransition.moveToNewPost();
    }

    @BindingAdapter("posts")
    public static void setPosts(ListView listView, List<Post> posts) {
        if (posts == null) {
            return;
        }
        ListAdapter adapter = listView.getAdapter();
        PostsAdapter postsAdapter;
        if (adapter == null || !(adapter instanceof PostsAdapter)) {
            postsAdapter = new PostsAdapter(listView.getContext());
            listView.setAdapter(postsAdapter);
        } else {
            postsAdapter = (PostsAdapter) adapter;
        }
        postsAdapter.setNotifyOnChange(false);
        postsAdapter.clear();
        postsAdapter.addAll(posts);
        postsAdapter.setNotifyOnChange(true);
        postsAdapter.notifyDataSetChanged();
    }

    public interface ScreenTransition {
        void moveToNewPost();
    }

}