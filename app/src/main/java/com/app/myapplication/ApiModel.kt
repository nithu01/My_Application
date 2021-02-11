package com.app.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ApiModel( val apiRepository :ApiRepository = ApiRepository()) : ViewModel(){

//    var apiRepository :ApiRepository?=null

//    fun ApiModel() {
//        apiRepository = ApiRepository()
//    }


    fun getdata(): MutableLiveData<PremiumRetailer>{
        return apiRepository!!.getdata()
    }
}

