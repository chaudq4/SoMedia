package com.chauduong.somedia.login;


import android.content.Context;

import androidx.annotation.NonNull;

import com.chauduong.somedia.model.User;
import com.chauduong.somedia.session.SessionManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginPresenterImp implements LoginPresenter {
    private static final String TAG = "LoginPresenterImp";
    private LoginView mLoginView;


    public LoginPresenterImp(LoginView mLoginView) {
        this.mLoginView = mLoginView;
    }

    @Override
    public void getPref(Context context) {

        mLoginView.getPref(SessionManager.getInstance(context).isLogin(), SessionManager.getInstance(context).isRemember(), SessionManager.getInstance(context).getUserName(), SessionManager.getInstance(context).getPassWord());

    }

    @Override
    public void setPref(boolean isLogin, boolean isRemember, String userName, String passWord, Context context) {
        SessionManager.getInstance(context).setLogin(isLogin, isRemember, userName, passWord);
    }


    @Override
    public void login(final String username, final String password) {
        if (username.length() == 0 || password.length() == 0) {
            mLoginView.signInError("Please insert username and password!");
            return;
        }
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("user");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean isResult = false;
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    User user = dataSnapshot1.getValue(User.class);
                    if (user.getUserName().equalsIgnoreCase(username) && user.getPassWord().equals(password)) {
                        isResult = true;
                        myRef.child(user.getId()).child("online").setValue(true);
                        user.setOnline(true);
                        mLoginView.signInSuccess(user);
                        myRef.removeEventListener(this);
                        break;
                    }
                }
                if (isResult == false) {
                    mLoginView.signInError("Wrong account. Please check information!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                mLoginView.signInError("Cannot connect to server. Please check network!");
            }
        });

    }


}
