
package com.app.myapplication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OperaterModal {

    @SerializedName("ststus")
    @Expose
    private String ststus;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<OperaterData> data = null;

    public String getStstus() {
        return ststus;
    }

    public void setStstus(String ststus) {
        this.ststus = ststus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<OperaterData> getData() {
        return data;
    }

    public void setData(List<OperaterData> data) {
        this.data = data;
    }

}
