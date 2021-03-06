package com.chauduong.somedia.model;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Newfeed implements Serializable, Comparable {
    private String id;
    private User mUser;
    private Date mDate;
    private String mContent;
    private String linkImage;

    public Newfeed() {
    }

    public Newfeed(User mUser, Date mDate, String mContent, String linkImage) {
        this.id = UUID.randomUUID().toString();
        this.mUser = mUser;
        this.mDate = mDate;
        this.mContent = mContent;
        this.linkImage = linkImage;
    }

    public User getmUser() {
        return mUser;
    }

    public void setmUser(User mUser) {
        this.mUser = mUser;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Newfeed) {
            Newfeed newfeed = (Newfeed) o;
            return this.getmDate().compareTo(((Newfeed) o).getmDate());
        }
        return 0;
    }
}
