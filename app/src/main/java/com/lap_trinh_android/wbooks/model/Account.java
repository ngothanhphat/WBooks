package com.lap_trinh_android.wbooks.model;

public class Account {

//    Các biến
    private int mId;
    private String mNameAccount;
    private String mPassword;
    private String mEmail;
    private int mAuthorization;

//    Hàm khởi tạo
    public Account(String mNameAccount, String mPassword, String mEmail, int mAuthorization) {
        this.mNameAccount = mNameAccount;
        this.mPassword = mPassword;
        this.mEmail = mEmail;
        this.mAuthorization = mAuthorization;
    }

    public Account(String mNameAccount, String mEmail) {
        this.mNameAccount = mNameAccount;
        this.mEmail = mEmail;
    }

//    Các getter và setter
    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmNameAccount() {
        return mNameAccount;
    }

    public void setmNameAccount(String mNameAccount) {
        this.mNameAccount = mNameAccount;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public int getmAuthorization() {
        return mAuthorization;
    }

    public void setmAuthorization(int mAuthorization) {
        this.mAuthorization = mAuthorization;
    }
}
