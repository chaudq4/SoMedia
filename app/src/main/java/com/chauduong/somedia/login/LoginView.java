package com.chauduong.somedia.login;

import com.chauduong.somedia.model.User;

public interface LoginView {
    void signInSuccess(User user);
    void signInError(String message);
    void registerSuccess();
    void getPref(boolean isLogin, String userName, String passWord);
}
