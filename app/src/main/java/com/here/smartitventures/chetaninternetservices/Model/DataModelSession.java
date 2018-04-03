package com.here.smartitventures.chetaninternetservices.Model;


public class DataModelSession {

    private String mdata,mdownloadBytes,muploadBytes;

    public DataModelSession(String mdata, String mdownloadBytes, String muploadBytes) {
        this.mdata = mdata;
        this.mdownloadBytes = mdownloadBytes;
        this.muploadBytes = muploadBytes;
    }

    public String getMdata() {
        return mdata;
    }

    public void setMdata(String mdata) {
        this.mdata = mdata;
    }

    public String getMdownloadBytes() {
        return mdownloadBytes;
    }

    public void setMdownloadBytes(String mdownloadBytes) {
        this.mdownloadBytes = mdownloadBytes;
    }

    public String getMuploadBytes() {
        return muploadBytes;
    }

    public void setMuploadBytes(String muploadBytes) {
        this.muploadBytes = muploadBytes;
    }
}
