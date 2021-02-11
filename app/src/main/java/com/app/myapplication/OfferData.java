package com.app.myapplication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OfferData {

    @SerializedName("offerid")
    @Expose
    private String offerid;
    @SerializedName("offername")
    @Expose
    private String offername;
    @SerializedName("offerimage")
    @Expose
    private String offerimage;

    public String getOfferid() {
        return offerid;
    }

    public void setOfferid(String offerid) {
        this.offerid = offerid;
    }

    public String getOffername() {
        return offername;
    }

    public void setOffername(String offername) {
        this.offername = offername;
    }

    public String getOfferimage() {
        return offerimage;
    }

    public void setOfferimage(String offerimage) {
        this.offerimage = offerimage;
    }

}
