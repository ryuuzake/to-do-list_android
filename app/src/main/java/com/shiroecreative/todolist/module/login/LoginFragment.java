package com.shiroecreative.todolist.module.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.common.SignInButton;
import com.google.android.material.textfield.TextInputEditText;
import com.shiroecreative.todolist.R;
import com.shiroecreative.todolist.base.BaseFragment;
import com.shiroecreative.todolist.module.home.HomeActivity;

public class LoginFragment extends BaseFragment<LoginActivity, LoginContract.Presenter> implements LoginContract.View {

    // For Academic Purpose
    private static final int RC_SIGN_IN = 69;
    private TextInputEditText etEmail;
    private TextInputEditText etPassword;
    private Button btnLogin;
    private SignInButton btnGoogleLogin;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.activity_login, container, false);
        presenter.start();

        etEmail = fragmentView.findViewById(R.id.et_email);
        etPassword = fragmentView.findViewById(R.id.et_password);
        btnLogin = fragmentView.findViewById(R.id.btn_login);
        btnGoogleLogin = fragmentView.findViewById(R.id.sign_in_button);
        btnGoogleLogin.setSize(SignInButton.SIZE_WIDE);
        btnLogin.setOnClickListener(this::setBtnLoginClick);
        btnGoogleLogin.setOnClickListener(v -> this.signInGoogle());

        setTitle(getResources().getString(R.string.login_title));

        return fragmentView;
    }

    @Override
    public void signInGoogle() {
        Intent intent = presenter.getSignInIntent();
        startActivityForResult(intent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            presenter.handleSignInResult(data);
        }
    }

    private void setBtnLoginClick(View view) {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        presenter.performLogin(email, password);
    }

    @Override
    public void redirectToHome() {
        Intent intent = new Intent(activity, HomeActivity.class);
        startActivity(intent);
        activity.finish();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
