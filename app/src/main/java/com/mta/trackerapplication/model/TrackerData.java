package com.mta.trackerapplication.model;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TrackerData {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String mobileNo = "";

    private float amount;
    private String paidFor;

    @NonNull
    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(@NonNull String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getPaidFor() {
        return paidFor;
    }

    public void setPaidFor(String paidFor) {
        this.paidFor = paidFor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
