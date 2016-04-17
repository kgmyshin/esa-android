/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/17.
 */

package com.kgmyshin.esa.presentation.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.SparseArray;

import com.kgmyshin.esa.BR;
import com.kgmyshin.esa.domain.dto.Page;
import com.kgmyshin.esa.domain.dto.Post;
import com.kgmyshin.esa.domain.repository.PostRepository;
import com.kgmyshin.esa.domain.repository.PostRepositoryFactory;
import com.kgmyshin.esa.domain.repository.TeamRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public class PostsViewModel extends BaseObservable {

    private PostRepository repository;
    private CompositeSubscription compositeSubscription = new CompositeSubscription();
    private int pageNumber;

    @Bindable
    private List<Post> posts;
    private SparseArray<Page<Post>> pageMap = new SparseArray<>();

    @Inject
    public PostsViewModel(TeamRepository teamRepository, PostRepositoryFactory postRepositoryFactory) {
        this.repository = postRepositoryFactory.create(teamRepository.find());
    }

    public void start() {
        pageNumber = 1;
        load();
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void loadNextIfNeed() {
        if (pageMap.size() == 0) {
            return;
        }
        Page<Post> page = pageMap.valueAt(pageMap.size() - 1);
        if (page.isHasNext() && pageNumber != page.getNextPage()) {
            pageNumber = page.getNextPage();
            load();
        }
    }

    public void stop() {
        compositeSubscription.clear();
    }

    private void load() {
        Subscription subscription = repository.findAll(pageNumber).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Page<Post>>() {
            @Override
            public void call(Page<Post> postPage) {
                pageMap.append(postPage.getPage(), postPage);
                posts = new ArrayList<>();
                for (int i = 0; i < pageMap.size(); i++) {
                    posts.addAll(pageMap.valueAt(i).getItems());
                }
                notifyPropertyChanged(BR.posts);
            }
        });
        compositeSubscription.add(subscription);
    }

}
