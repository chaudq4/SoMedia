package com.chauduong.somedia.login;

import android.content.Context;

import com.chauduong.somedia.model.User;

public interface LoginPresenter {
    void getPref(Context context);
    void setPref(boolean isLogin, boolean isRemember, String userName, String passWord,Context context);
    void login(String username, String password);
    void register(User user);
}
