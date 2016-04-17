/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/09.
 */

package com.kgmyshin.esa.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.kgmyshin.esa.Esa;
import com.kgmyshin.esa.R;
import com.kgmyshin.esa.presenter.LoginPresenter;

import javax.inject.Inject;

public class LoginFragment extends Fragment {

    private EditText accessTokenEditText;
    private EditText teamNameEditText;
    private FrameLayout progressBarContainer;

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
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        accessTokenEditText = (EditText) view.findViewById(R.id.access_token_edit_text);
        teamNameEditText = (EditText) view.findViewById(R.id.team_name_edit_text);
        progressBarContainer = (FrameLayout) view.findViewById(R.id.progressbar_container);

        view.findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClickLogin(accessTokenEditText.getText().toString(), teamNameEditText.getText().toString());
            }
        });

        return view;
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
        progressBarContainer.setVisibility(View.VISIBLE);
    }

    public void dismissLoading() {
        progressBarContainer.setVisibility(View.GONE);
    }

    public void showErrorDialog(int messageResId) {
        new AlertDialog.Builder(getContext())
                .setMessage(messageResId)
                .show();
    }

}
