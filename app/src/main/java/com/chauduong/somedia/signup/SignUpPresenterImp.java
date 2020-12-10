package com.chauduong.somedia.signup;

import android.util.Log;

import com.chauduong.somedia.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpPresenterImp implements SignUpPresenter {
    private static final String TAG = "SignUpPresenterImp" ;
    private SignUpView mSignUpView;

    public SignUpPresenterImp(SignUpView mSignUpView) {
        this.mSignUpView = mSignUpView;
    }

    @Override
    public void signUp(User user) {
        if (checkValid(user)) {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("user");
            String id = myRef.push().getKey();
            Log.d(TAG, "signUp: "+ id);
            user.setId(id);
            myRef.child(id).setValue(user);
            mSignUpView.signUpSuccess(user);
        }
        ;
    }

    private boolean checkValid(User user) {
        if (user.getFullName().length() == 0 || user.getUserName().length() == 0 || user.getPassWord().length() == 0) {
            mSignUpView.signUpError("Vui lòng nhập đủ thông tin đăng ký");
            return false;
        }
        return true;
    }
}
