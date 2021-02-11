package com.app.myapplication

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

//data class User(val name:String,val address:String)


class User {

    @SerializedName("status")
    var status:String?=null

    @SerializedName("message")
    var message:String?=null


}