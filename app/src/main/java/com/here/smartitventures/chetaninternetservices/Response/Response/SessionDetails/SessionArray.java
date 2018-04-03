package com.here.smartitventures.chetaninternetservices.Response.Response.SessionDetails;

import java.io.Serializable;

/**
 * Created by dharamveer on 3/8/17.
 */

public class SessionArray implements Serializable{

    String downloadBytes;
    String uploadBytes;
    String start_time;

    public SessionArray() {
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

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public SessionArray(String downloadBytes, String uploadBytes, String start_time) {
        this.downloadBytes = downloadBytes;
        this.uploadBytes = uploadBytes;
        this.start_time = start_time;

    }
}