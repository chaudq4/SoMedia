package com.chauduong.somedia.model;

import java.io.Serializable;

public class User implements Serializable {
    private String id;
    private String userName;
    private String passWord;
    private String linkAvt;
    private String fullName;
    private String phone;
    private boolean isOnline;

    public User(String id, String userName, String passWord) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
    }


    public User() {
    }

    public User(String userName, String passWord, String linkAvt, String fullName) {
        this.userName = userName;
        this.passWord = passWord;
        this.linkAvt = linkAvt;
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getLinkAvt() {
        return linkAvt;
    }

    public void setLinkAvt(String linkAvt) {
        this.linkAvt = linkAvt;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public User(String userName, String passWord, String linkAvt, String fullName, String phone) {

        this.userName = userName;
        this.passWord = passWord;
        this.linkAvt = linkAvt;
        this.fullName = fullName;
        this.phone = phone;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
