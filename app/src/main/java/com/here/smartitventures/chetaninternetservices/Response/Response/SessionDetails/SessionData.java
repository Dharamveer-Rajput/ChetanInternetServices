package com.here.smartitventures.chetaninternetservices.Response.Response.SessionDetails;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class SessionData {

    @SerializedName("acct_session_id")
    @Expose
    private String acctSessionId;
    @SerializedName("original_acct_session_id")
    @Expose
    private String originalAcctSessionId;
    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("stop_time")
    @Expose
    private String stopTime;
    @SerializedName("ip_addr")
    @Expose
    private String ipAddr;
    @SerializedName("mac")
    @Expose
    private String mac;
    @SerializedName("session_timeout")
    @Expose
    private String sessionTimeout;
    @SerializedName("download_bytes")
    @Expose
    private String downloadBytes;
    @SerializedName("upload_bytes")
    @Expose
    private String uploadBytes;
    @SerializedName("nas_ip_address")
    @Expose
    private String nasIpAddress;
    @SerializedName("nas_mac")
    @Expose
    private String nasMac;
    @SerializedName("terminate_cause")
    @Expose
    private String terminateCause;
    @SerializedName("browser")
    @Expose
    private String browser;
    @SerializedName("browser_version")
    @Expose
    private Object browserVersion;
    @SerializedName("operating_system")
    @Expose
    private String operatingSystem;
    @SerializedName("device_type")
    @Expose
    private String deviceType;
    @SerializedName("login_type")
    @Expose
    private Object loginType;
    @SerializedName("authtype")
    @Expose
    private String authtype;
    @SerializedName("is_mac_auth")
    @Expose
    private Boolean isMacAuth;
    @SerializedName("override_bandwidth_level")
    @Expose
    private Boolean overrideBandwidthLevel;
    @SerializedName("upload_rate")
    @Expose
    private String uploadRate;
    @SerializedName("download_rate")
    @Expose
    private String downloadRate;

    public String getAcctSessionId() {
        return acctSessionId;
    }

    public void setAcctSessionId(String acctSessionId) {
        this.acctSessionId = acctSessionId;
    }

    public String getOriginalAcctSessionId() {
        return originalAcctSessionId;
    }

    public void setOriginalAcctSessionId(String originalAcctSessionId) {
        this.originalAcctSessionId = originalAcctSessionId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(String sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public String getDownloadBytes() {
        return downloadBytes;
    }

    public void setDownloadBytes(String downloadBytes) {
        this.downloadBytes = downloadBytes;
    }

    public String getUploadBytes() {
        return uploadBytes;
    }

    public void setUploadBytes(String uploadBytes) {
        this.uploadBytes = uploadBytes;
    }

    public String getNasIpAddress() {
        return nasIpAddress;
    }

    public void setNasIpAddress(String nasIpAddress) {
        this.nasIpAddress = nasIpAddress;
    }

    public String getNasMac() {
        return nasMac;
    }

    public void setNasMac(String nasMac) {
        this.nasMac = nasMac;
    }

    public String getTerminateCause() {
        return terminateCause;
    }

    public void setTerminateCause(String terminateCause) {
        this.terminateCause = terminateCause;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public Object getBrowserVersion() {
        return browserVersion;
    }

    public void setBrowserVersion(Object browserVersion) {
        this.browserVersion = browserVersion;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public Object getLoginType() {
        return loginType;
    }

    public void setLoginType(Object loginType) {
        this.loginType = loginType;
    }

    public String getAuthtype() {
        return authtype;
    }

    public void setAuthtype(String authtype) {
        this.authtype = authtype;
    }

    public Boolean getIsMacAuth() {
        return isMacAuth;
    }

    public void setIsMacAuth(Boolean isMacAuth) {
        this.isMacAuth = isMacAuth;
    }

    public Boolean getOverrideBandwidthLevel() {
        return overrideBandwidthLevel;
    }

    public void setOverrideBandwidthLevel(Boolean overrideBandwidthLevel) {
        this.overrideBandwidthLevel = overrideBandwidthLevel;
    }

    public String getUploadRate() {
        return uploadRate;
    }

    public void setUploadRate(String uploadRate) {
        this.uploadRate = uploadRate;
    }

    public String getDownloadRate() {
        return downloadRate;
    }

    public void setDownloadRate(String downloadRate) {
        this.downloadRate = downloadRate;
    }

}