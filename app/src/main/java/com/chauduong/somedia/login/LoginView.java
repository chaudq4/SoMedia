package com.chauduong.somedia.login;

import android.content.Context;

import com.chauduong.somedia.model.User;

public interface LoginView {
    void signInSuccess(User user);
    void signInError(String message);
    void getPref(boolean isLogin, boolean isRemember, String userName, String passWord);
    Context getContext();
}
