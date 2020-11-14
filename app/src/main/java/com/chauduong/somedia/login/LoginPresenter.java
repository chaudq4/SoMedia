package com.chauduong.somedia.login;

import android.content.Context;

public interface LoginPresenter {
    void getPref(Context context);
    void setPref(boolean isLogin, String userName, String passWord,Context context);
    void login(String username, String password);
}
