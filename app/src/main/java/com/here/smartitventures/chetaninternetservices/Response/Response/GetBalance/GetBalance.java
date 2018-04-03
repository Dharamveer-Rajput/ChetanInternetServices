package com.here.smartitventures.chetaninternetservices.Response.Response.GetBalance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.here.smartitventures.chetaninternetservices.Response.Response.GetProfileDetails.CurrentBillingCycleUsage;

public class GetBalance {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("balance")
    @Expose
    private float balance;


    private static GetBalance instance = null;

    public static GetBalance getInstance() {
        if (instance == null) {
            instance = new GetBalance();
        }
        return instance;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

}