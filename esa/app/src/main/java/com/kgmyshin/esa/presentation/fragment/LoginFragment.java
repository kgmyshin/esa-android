/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/09.
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
import com.kgmyshin.esa.databinding.FragmentLoginBinding;
import com.kgmyshin.esa.presentation.presenter.LoginPresenter;

import javax.inject.Inject;

public class LoginFragment extends Fragment {

    private ScreenTransition screenTransition;

    private FragmentLoginBinding binding;

    @Inject
    LoginPresenter presenter;

    public static LoginFragment newInstance() {
        return new LoginFragment();
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
        binding = FragmentLoginBinding.inflate(inflater, container, false);

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClickLogin(
                        binding.accessTokenEditText.getText().toString(),
                        binding.teamNameEditText.getText().toString()
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

    public void showErrorDialog(int messageResId) {
        new AlertDialog.Builder(getContext())
                .setMessage(messageResId)
                .show();
    }

    public void moveToPosts() {
        screenTransition.moveToPosts();
    }

    public interface ScreenTransition {
        void moveToPosts();
    }

}
