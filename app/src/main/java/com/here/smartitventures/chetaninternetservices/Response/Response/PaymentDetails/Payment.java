package com.here.smartitventures.chetaninternetservices.Response.Response.PaymentDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Payment {

    @SerializedName("PaymentInfoCheque")
    @Expose
    private PaymentInfoCheque paymentInfoCheque;

    public PaymentInfoCheque getPaymentInfoCheque() {
        return paymentInfoCheque;
    }

    public void setPaymentInfoCheque(PaymentInfoCheque paymentInfoCheque) {
        this.paymentInfoCheque = paymentInfoCheque;
    }

}



