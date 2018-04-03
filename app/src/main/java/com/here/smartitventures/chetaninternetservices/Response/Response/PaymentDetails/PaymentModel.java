package com.here.smartitventures.chetaninternetservices.Response.Response.PaymentDetails;

import com.here.smartitventures.chetaninternetservices.Response.Response.GetProfileDetails.CurrentBillingCycleUsage;

import java.io.Serializable;

/**
 * Created by dharamveer on 9/8/17.
 */

public class PaymentModel implements Serializable{


    String id_payment;
    String date;

    String method_type;

    String amount;

    private static PaymentModel instance = null;


    public static PaymentModel getInstance() {
        if (instance == null) {
            instance = new PaymentModel();
        }
        return instance;
    }

    public PaymentModel(String id_payment, String date, String method_type, String amount) {
        this.id_payment = id_payment;
        this.date = date;
        this.method_type = method_type;
        this.amount = amount;
    }

    public PaymentModel() {

    }

    public String getId_payment() {
        return id_payment;
    }

    public void setId_payment(String id_payment) {
        this.id_payment = id_payment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMethod_type() {
        return method_type;
    }

    public void setMethod_type(String method_type) {
        this.method_type = method_type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
