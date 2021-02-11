package com.app.myapplication

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OperatorModal {

    @SerializedName("ststus")
    @Expose
    private var ststus: String? = null

    @SerializedName("message")
    @Expose
    private var message: String? = null

    @SerializedName("data")
    @Expose
    private var data: List<OperaterData?>? = null

    fun getStstus(): String? {
        return ststus
    }

    fun setStstus(ststus: String?) {
        this.ststus = ststus
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String?) {
        this.message = message
    }

    fun getData(): List<OperaterData?>? {
        return data
    }

    fun setData(data: List<OperaterData?>?) {
        this.data = data
    }
}