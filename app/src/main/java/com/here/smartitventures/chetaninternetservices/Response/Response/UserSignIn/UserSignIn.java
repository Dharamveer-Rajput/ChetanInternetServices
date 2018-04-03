package com.here.smartitventures.chetaninternetservices.Response.Response.UserSignIn;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserSignIn {

    @SerializedName("success")
    @Expose
    private String success;

    public UserSignIn(String s, String s1) {
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

}



