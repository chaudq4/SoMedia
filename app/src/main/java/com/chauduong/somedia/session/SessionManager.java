package com.chauduong.somedia.session;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.chauduong.somedia.keycode.Constant;

public class SessionManager {
    private Context mContext;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private static final int PRE_MODE = 1;


    @SuppressLint("WrongConstant")
    public SessionManager(Context mContext) {
        this.mContext = mContext;
        mSharedPreferences = mContext.getSharedPreferences(Constant.PREF_LOGIN, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

    }

    public void setLogin(boolean isLogin, String userName, String password) {
        mEditor.putBoolean(Constant.KEY_IS_LOGIN, isLogin);
        mEditor.putString(Constant.KEY_USERNAME, userName);
        mEditor.putString(Constant.KEY_PASSWORD, password);
        mEditor.commit();
        mEditor.apply();

    }

    public boolean isLogin() {
        return mSharedPreferences.getBoolean(Constant.KEY_IS_LOGIN, false);
    }
    public String getUserName() {
        return mSharedPreferences.getString(Constant.KEY_USERNAME,"");
    }
    public String getPassWord() {
        return mSharedPreferences.getString(Constant.KEY_PASSWORD,"");
    }
}
