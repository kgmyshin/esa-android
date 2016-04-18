/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/19.
 */

package com.kgmyshin.esa.presentation.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kgmyshin.esa.Esa;
import com.kgmyshin.esa.R;
import com.kgmyshin.esa.databinding.FragmentNewPostBinding;
import com.kgmyshin.esa.presentation.presenter.NewPostPresenter;

import javax.inject.Inject;

public class NewPostFragment extends Fragment {

    private FragmentNewPostBinding binding;
    private ScreenTransition screenTransition;
    @Inject
    NewPostPresenter presenter;

    public static NewPostFragment newInstance() {
        return new NewPostFragment();
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
        binding = FragmentNewPostBinding.inflate(inflater, container, false);

        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClickSave(
                        binding.titleEditText.getText().toString(),
                        binding.bodyEditText.getText().toString()
                );
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onPause() {
        presenter.onPause();
        super.onPause();
    }

    public void showLoading() {
        binding.progressbarContainer.setVisibility(View.VISIBLE);
    }

    public void dismissLoading() {
        binding.progressbarContainer.setVisibility(View.GONE);
    }

    public void showNetworkErrorDialog() {
        new AlertDialog.Builder(getContext())
                .setMessage(R.string.error＿_network)
                .show();
    }

    public void finish() {
        screenTransition.finish(this);
    }

    public interface ScreenTransition {
        void finish(NewPostFragment from);
    }
}
