package com.mta.trackerapplication.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @NonNull
    @PrimaryKey
    private String mobileNo = "";

    private String name;

    private String email;

    @NonNull
    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(@NonNull String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
