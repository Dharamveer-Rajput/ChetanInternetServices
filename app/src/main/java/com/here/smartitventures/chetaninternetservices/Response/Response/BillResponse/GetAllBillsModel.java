package com.here.smartitventures.chetaninternetservices.Response.Response.BillResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllBillsModel {

    @SerializedName("474")
    @Expose
    private String _474;
    @SerializedName("954")
    @Expose
    private String _954;
    @SerializedName("1462")
    @Expose
    private String _1462;
    @SerializedName("2037")
    @Expose
    private String _2037;
    @SerializedName("2476")
    @Expose
    private String _2476;


    private static GetAllBillsModel instance = null;

    public static GetAllBillsModel getInstance() {
        if (instance == null) {
            instance = new GetAllBillsModel();
        }
        return instance;
    }


    public String get474() {
        return _474;
    }

    public void set474(String _474) {
        this._474 = _474;
    }

    public String get954() {
        return _954;
    }

    public void set954(String _954) {
        this._954 = _954;
    }

    public String get1462() {
        return _1462;
    }

    public void set1462(String _1462) {
        this._1462 = _1462;
    }

    public String get2037() {
        return _2037;
    }

    public void set2037(String _2037) {
        this._2037 = _2037;
    }

    public String get2476() {
        return _2476;
    }

    public void set2476(String _2476) {
        this._2476 = _2476;
    }

}


