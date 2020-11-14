package com.chauduong.somedia.home.newfeed;

import com.chauduong.somedia.model.Newfeed;

public interface NewfeedPresenter  {
    void getNew();
    void addNew(Newfeed newfeed);
    void deleteNew(Newfeed newfeed);
    void updateNew(Newfeed newfeed);
}
