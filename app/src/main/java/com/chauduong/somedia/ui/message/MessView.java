package com.chauduong.somedia.ui.message;

import com.chauduong.somedia.model.Mess;
import com.chauduong.somedia.model.User;

public interface MessView {
    void update(Mess mess);

    void addUserOnline(User user);

    void updateUserOnline(User user);
}
