package com.evolet.myapplication.Items;

public class UserDetails {

    String mUserName;
    String mPhone;
    String mAddress;
    public UserDetails(String mUserName, String mPhone, String mAddress) {
        this.mUserName = mUserName;
        this.mPhone = mPhone;
        this.mAddress = mAddress;
    }

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }
}
