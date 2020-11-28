package com.chauduong.somedia.signup;

import com.chauduong.somedia.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpPresenterImp implements SignUpPresenter {
    private SignUpView mSignUpView;

    public SignUpPresenterImp(SignUpView mSignUpView) {
        this.mSignUpView = mSignUpView;
    }

    @Override
    public void signUp(User user) {
        if (checkValid(user)) {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("user");
            myRef.push().setValue(user);
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
