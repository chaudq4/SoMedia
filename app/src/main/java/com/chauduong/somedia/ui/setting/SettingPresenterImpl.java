package com.chauduong.somedia.ui.setting;

import android.content.Context;

import com.chauduong.somedia.session.SessionManager;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SettingPresenterImpl implements SettingPresenter {
    private SettingView mSettingView;

    public SettingPresenterImpl(SettingView settingView) {
        this.mSettingView = settingView;
    }

    @Override
    public void logout(Context context) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("user");
        myRef.child(SessionManager.getInstance(context).getmUser().getId()).child("online").setValue(false);
        SessionManager.getInstance(context).setIsLogin(false);;
        mSettingView.exitApp();
    }
}
