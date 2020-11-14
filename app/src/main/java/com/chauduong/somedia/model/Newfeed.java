package com.chauduong.somedia.model;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Newfeed implements Serializable {
    private String id;
    private User mUser;
    private Date mDate;
    private String mContent;
    private String linkImage;

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

    static DiffUtil.ItemCallback<Newfeed> DIFF_CALLBACK = new DiffUtil.ItemCallback<Newfeed>() {

        @Override
        public boolean areItemsTheSame(@NonNull Newfeed oldItem, @NonNull Newfeed newItem) {
            if (oldItem.id.equals(newItem.id)) return true;
            return false;
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Newfeed oldItem, @NonNull Newfeed newItem) {
            return oldItem.equals(newItem);
        }
    };
}
