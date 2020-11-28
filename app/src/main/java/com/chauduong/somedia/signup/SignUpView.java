package com.chauduong.somedia.signup;

import com.chauduong.somedia.model.User;

public interface SignUpView {
    void signUpSuccess(User user);
    void signUpError(String message);
}
