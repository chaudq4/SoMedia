package com.chauduong.somedia.home.newfeed;

import androidx.lifecycle.MutableLiveData;

import com.chauduong.somedia.model.Newfeed;

import java.util.List;

public interface NewfeedView {
    void updateData(List<Newfeed> listMutableLiveData);
}
