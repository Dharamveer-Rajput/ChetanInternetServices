package com.here.smartitventures.chetaninternetservices.Response.Response.GetProfileDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CurrentBillingCycleUsage {

    @SerializedName("bandwidthTemplateId")
    @Expose
    private String bandwidthTemplateId;
    @SerializedName("bandwidthTemplateName")
    @Expose
    private String bandwidthTemplateName;
    @SerializedName("bandwidthTemplateMikrotik")
    @Expose
    private String bandwidthTemplateMikrotik;
    @SerializedName("billingResetType")
    @Expose
    private String billingResetType;
    @SerializedName("billingStartDate")
    @Expose
    private String billingStartDate;
    @SerializedName("billingEndDate")
    @Expose
    private String billingEndDate;
    @SerializedName("uploadDataUsage")
    @Expose
    private String uploadDataUsage;
    @SerializedName("downloadDataUsage")
    @Expose
    private String downloadDataUsage;
    @SerializedName("totalDataUsage")
    @Expose
    private Double totalDataUsage;
    @SerializedName("bandwidthDataLimit")
    @Expose
    private String bandwidthDataLimit;
    @SerializedName("usedBandwidthQuota")
    @Expose
    private Integer usedBandwidthQuota;
    @SerializedName("totalBandwidthQuota")
    @Expose
    private Long totalBandwidthQuota;

    private static CurrentBillingCycleUsage instance = null;

    public static CurrentBillingCycleUsage getInstance() {
        if (instance == null) {
            instance = new CurrentBillingCycleUsage();
        }
        return instance;
    }



    public String getBandwidthTemplateId() {
        return bandwidthTemplateId;
    }

    public void setBandwidthTemplateId(String bandwidthTemplateId) {
        this.bandwidthTemplateId = bandwidthTemplateId;
    }

    public String getBandwidthTemplateName() {
        return bandwidthTemplateName;
    }

    public void setBandwidthTemplateName(String bandwidthTemplateName) {
        this.bandwidthTemplateName = bandwidthTemplateName;
    }

    public String getBandwidthTemplateMikrotik() {
        return bandwidthTemplateMikrotik;
    }

    public void setBandwidthTemplateMikrotik(String bandwidthTemplateMikrotik) {
        this.bandwidthTemplateMikrotik = bandwidthTemplateMikrotik;
    }

    public String getBillingResetType() {
        return billingResetType;
    }

    public void setBillingResetType(String billingResetType) {
        this.billingResetType = billingResetType;
    }

    public String getBillingStartDate() {
        return billingStartDate;
    }

    public void setBillingStartDate(String billingStartDate) {
        this.billingStartDate = billingStartDate;
    }

    public String getBillingEndDate() {
        return billingEndDate;
    }

    public void setBillingEndDate(String billingEndDate) {
        this.billingEndDate = billingEndDate;
    }

    public String getUploadDataUsage() {
        return uploadDataUsage;
    }

    public void setUploadDataUsage(String uploadDataUsage) {
        this.uploadDataUsage = uploadDataUsage;
    }

    public String getDownloadDataUsage() {
        return downloadDataUsage;
    }

    public void setDownloadDataUsage(String downloadDataUsage) {
        this.downloadDataUsage = downloadDataUsage;
    }

    public Double getTotalDataUsage() {
        return totalDataUsage;
    }

    public void setTotalDataUsage(Double totalDataUsage) {
        this.totalDataUsage = totalDataUsage;
    }

    public String getBandwidthDataLimit() {
        return bandwidthDataLimit;
    }

    public void setBandwidthDataLimit(String bandwidthDataLimit) {
        this.bandwidthDataLimit = bandwidthDataLimit;
    }

    public Integer getUsedBandwidthQuota() {
        return usedBandwidthQuota;
    }

    public void setUsedBandwidthQuota(Integer usedBandwidthQuota) {
        this.usedBandwidthQuota = usedBandwidthQuota;
    }

    public Long getTotalBandwidthQuota() {
        return totalBandwidthQuota;
    }

    public void setTotalBandwidthQuota(Long totalBandwidthQuota) {
        this.totalBandwidthQuota = totalBandwidthQuota;
    }

}
