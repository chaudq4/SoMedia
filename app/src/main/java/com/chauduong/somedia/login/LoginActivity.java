package com.chauduong.somedia.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;

import com.chauduong.somedia.R;
import com.chauduong.somedia.databinding.ActivityLoginBinding;
import com.chauduong.somedia.keycode.Constant;
import com.chauduong.somedia.model.User;
import com.chauduong.somedia.MainActivity;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends Activity implements LoginView, View.OnClickListener {
    ActivityLoginBinding mActivityLoginBinding;
    LoginPresenterImp mLoginPresenterImp;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        mActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        initPresenter();
        registerListener();
        mLoginPresenterImp.getPref(getApplicationContext());
//        mLoginPresenterImp.register(new User("chaudq4","123",null,"Dương Quang Châu"));
//        mLoginPresenterImp.register(new User("an","123",null,"Nguyễn Văn A"));
//        mLoginPresenterImp.register(new User("thai","123",null,"Bui Thái"));
    }


    private void registerListener() {
        if (mActivityLoginBinding != null) {
            mActivityLoginBinding.btnSignUp.setOnClickListener(this);
        }
    }

    private void initPresenter() {
        mLoginPresenterImp = new LoginPresenterImp(this);
    }


    @Override
    public void signInSuccess(User user) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(Constant.KEY_USER_LOGIN, user);
        boolean isRemember = mActivityLoginBinding.cbRemember.isChecked();
        mLoginPresenterImp.setPref(true, isRemember, user.getUserName(), user.getPassWord(), getApplicationContext());
        startActivity(intent);
        finish();
    }

    @Override
    public void signInError(String message) {
        Toasty.warning(this, message).show();
    }

    @Override
    public void registerSuccess() {

    }

    @Override
    public void getPref(boolean isLogin, boolean isRemember, String userName, String passWord) {
        if (isLogin) {
            mLoginPresenterImp.login(userName, passWord);
        }
        else if(isRemember){
            mActivityLoginBinding.cbRemember.setChecked(isRemember);
            mActivityLoginBinding.edtUsername.setText(userName);
            mActivityLoginBinding.edtPassword.setText(passWord);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSignUp:
                String userName = mActivityLoginBinding.edtUsername.getText().toString();
                String passWord = mActivityLoginBinding.edtPassword.getText().toString();
                mLoginPresenterImp.login(userName, passWord);
                break;
            default:
                break;
        }
    }
}
