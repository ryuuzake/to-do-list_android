package com.shiroecreative.todolist.module.register;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;
import com.shiroecreative.todolist.R;
import com.shiroecreative.todolist.base.BaseFragment;

public class RegisterFragment extends BaseFragment<RegisterActivity, RegisterContract.Presenter>
        implements RegisterContract.View {

    private TextInputEditText etUsername;
    private TextInputEditText etEmail;
    private TextInputEditText etPassword;
    private TextInputEditText etConfirmPassword;
    private Button btnRegister;
    private View tvLoginClick;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.activity_register, container, false);
        return fragmentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        etUsername = fragmentView.findViewById(R.id.et_username);
        etEmail = fragmentView.findViewById(R.id.et_email);
        etPassword = fragmentView.findViewById(R.id.et_password);
        etConfirmPassword = fragmentView.findViewById(R.id.et_confirm_password);
        btnRegister = fragmentView.findViewById(R.id.btn_register);
        tvLoginClick = fragmentView.findViewById(R.id.tv_login_click);
        btnRegister.setOnClickListener(this::setBtnRegisterClick);
        tvLoginClick.setOnClickListener(v -> redirectToLogin());

        setTitle(getResources().getString(R.string.register_title));
    }

    private void setBtnRegisterClick(View view) {
        final Editable username = etUsername.getText();
        final Editable email = etEmail.getText();
        final Editable password = etPassword.getText();
        final Editable confirmPassword = etConfirmPassword.getText();
        if (performRegisterCheck(username, email, password, confirmPassword)) {
            presenter.performRegister(
                    email.toString(),
                    username.toString(),
                    password.toString(),
                    confirmPassword.toString()
            );
        }
    }

    private boolean performRegisterCheck(Editable username, Editable email, Editable password, Editable confirmPassword) {
        if (username != null && email != null && password != null && confirmPassword != null) {
            if (password.toString().equals(confirmPassword.toString())) {
                return true;
            } else {
                showError("Password Not same");
            }
        }
        showError("Missing Required Field");
        return false;
    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void redirectToLogin() {
        activity.finish();
    }
}
