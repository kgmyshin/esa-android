/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/18.
 */

package com.kgmyshin.esa.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.kgmyshin.esa.R;
import com.kgmyshin.esa.databinding.ViewPostBinding;
import com.kgmyshin.esa.dto.Post;

public class PostsAdapter extends ArrayAdapter<Post> {

    private LayoutInflater inflater;

    public PostsAdapter(Context context) {
        super(context, 0);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            ViewPostBinding binding = DataBindingUtil.inflate(inflater, R.layout.view_post, parent, false);
            convertView = binding.getRoot();
        }
        ViewPostBinding binding = DataBindingUtil.getBinding(convertView);
        Post post = getItem(position);
        binding.setPost(post);
        return convertView;
    }

}
