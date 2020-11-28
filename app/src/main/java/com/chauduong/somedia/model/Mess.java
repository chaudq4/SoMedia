package com.chauduong.somedia.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Mess implements Serializable {
    private String id;
    private User mUser;
    private Date mDate;
    private String mContent;
    private String linkImage;
    private boolean isExpanded;

    public Mess() {
    }

    @Override
    public String toString() {
        return "Mess{" +
                "id='" + id + '\'' +
                ", mUser=" + mUser +
                ", mDate=" + mDate +
                ", mContent='" + mContent + '\'' +
                ", linkImage='" + linkImage + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Mess(User mUser, Date mDate, String mContent, String linkImage) {
        this.id = UUID.randomUUID().toString();
        this.mUser = mUser;
        this.mDate = mDate;
        this.mContent = mContent;
        this.linkImage = linkImage;
        isExpanded = false;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }
}
