package com.here.smartitventures.chetaninternetservices.Response.Response.PaymentDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentInfoCheque {

    @SerializedName("payment_id")
    @Expose
    private String paymentId;
    @SerializedName("bank")
    @Expose
    private String bank;

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

}