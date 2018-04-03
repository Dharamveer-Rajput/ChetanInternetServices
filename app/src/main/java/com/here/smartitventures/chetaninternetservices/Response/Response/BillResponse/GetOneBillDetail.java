package com.here.smartitventures.chetaninternetservices.Response.Response.BillResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetOneBillDetail {

    @SerializedName("invoiceNumber")
    @Expose
    private String invoiceNumber;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("billGeneratedDate")
    @Expose
    private String billGeneratedDate;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("circuit_id")
    @Expose
    private String circuitId;
    @SerializedName("billingPeriodFrom")
    @Expose
    private String billingPeriodFrom;
    @SerializedName("billingPeriodTo")
    @Expose
    private String billingPeriodTo;
    @SerializedName("billingDueDate")
    @Expose
    private Object billingDueDate;
    @SerializedName("status")
    @Expose
    private String status;

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBillGeneratedDate() {
        return billGeneratedDate;
    }

    public void setBillGeneratedDate(String billGeneratedDate) {
        this.billGeneratedDate = billGeneratedDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCircuitId() {
        return circuitId;
    }

    public void setCircuitId(String circuitId) {
        this.circuitId = circuitId;
    }

    public String getBillingPeriodFrom() {
        return billingPeriodFrom;
    }

    public void setBillingPeriodFrom(String billingPeriodFrom) {
        this.billingPeriodFrom = billingPeriodFrom;
    }

    public String getBillingPeriodTo() {
        return billingPeriodTo;
    }

    public void setBillingPeriodTo(String billingPeriodTo) {
        this.billingPeriodTo = billingPeriodTo;
    }

    public Object getBillingDueDate() {
        return billingDueDate;
    }

    public void setBillingDueDate(Object billingDueDate) {
        this.billingDueDate = billingDueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

