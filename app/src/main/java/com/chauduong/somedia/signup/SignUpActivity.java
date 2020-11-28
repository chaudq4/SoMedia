package com.chauduong.somedia.signup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;

import com.chauduong.somedia.R;
import com.chauduong.somedia.databinding.ActivitySignupBinding;
import com.chauduong.somedia.keycode.Constant;
import com.chauduong.somedia.login.LoginActivity;
import com.chauduong.somedia.model.User;

import es.dmoral.toasty.Toasty;

public class SignUpActivity extends Activity implements View.OnClickListener, SignUpView {
    ActivitySignupBinding mActivitySignupBinding;
    SignUpPresenterImp mSignUpPresenterImp;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivitySignupBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup);
        registerListener();
        initPresenter();
    }


    private void initPresenter() {
        mSignUpPresenterImp = new SignUpPresenterImp(this);
    }

    private void registerListener() {
        mActivitySignupBinding.btnSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSignUp:
                String userName = mActivitySignupBinding.edtUsername.getText().toString();
                String passWord = mActivitySignupBinding.edtPassword.getText().toString();
                String phone = mActivitySignupBinding.edtPhone.getText().toString();
                String fullName = mActivitySignupBinding.edtFullName.getText().toString();
                User user = new User(userName, passWord, null, fullName, phone);
                mSignUpPresenterImp.signUp(user);
                break;
        }
    }

    @Override
    public void signUpSuccess(User user) {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra(Constant.KEY_USER_RESULT_SIGNUP, user);
        setResult(Activity.RESULT_OK, intent);
        finish();

    }

    @Override
    public void signUpError(String message) {
        Toasty.warning(this, message).show();
        return;
    }
}
