package com.chauduong.somedia.ui.updateInfo;

import com.chauduong.somedia.model.User;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChangeInfoPresenterImpl implements ChangeInfoPresenter {
    private ChangeInfoView mChangeInfoView;

    public ChangeInfoPresenterImpl(ChangeInfoView mChangeInfoView) {
        this.mChangeInfoView = mChangeInfoView;
    }

    @Override
    public void updateInfo(User user) {
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = mFirebaseDatabase.getReference("user");
        databaseReference.child(user.getId()).child("passWord").setValue(user.getPassWord());
        databaseReference.child(user.getId()).child("phone").setValue(user.getPhone());
        databaseReference.child(user.getId()).child("fullName").setValue(user.getFullName());
        mChangeInfoView.onUpdateSuccess(user);
    }
}
