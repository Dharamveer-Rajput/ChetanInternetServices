package com.here.smartitventures.chetaninternetservices.Response.Response.GetProfileDetails;

import android.content.Context;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("groupId")
    @Expose
    private String groupId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("address_line1")
    @Expose
    private String addressLine1;
    @SerializedName("address_line2")
    @Expose
    private String addressLine2;
    @SerializedName("address_city")
    @Expose
    private String addressCity;
    @SerializedName("address_pin")
    @Expose
    private String addressPin;
    @SerializedName("address_state")
    @Expose
    private String addressState;
    @SerializedName("comments")
    @Expose
    private String comments;
    @SerializedName("activationTime")
    @Expose
    private String activationTime;
    @SerializedName("expirationTime")
    @Expose
    private String expirationTime;
    @SerializedName("billing_id")
    @Expose
    private Object billingId;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("company_name")
    @Expose
    private String companyName;
    @SerializedName("CAF No")
    @Expose
    private String cAFNo;

    private static User instance = null;

    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressPin() {
        return addressPin;
    }

    public void setAddressPin(String addressPin) {
        this.addressPin = addressPin;
    }

    public String getAddressState() {
        return addressState;
    }

    public void setAddressState(String addressState) {
        this.addressState = addressState;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getActivationTime() {
        return activationTime;
    }

    public void setActivationTime(String activationTime) {
        this.activationTime = activationTime;
    }

    public String getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(String expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Object getBillingId() {
        return billingId;
    }

    public void setBillingId(Object billingId) {
        this.billingId = billingId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCAFNo() {
        return cAFNo;
    }

    public void setCAFNo(String cAFNo) {
        this.cAFNo = cAFNo;
    }

}

