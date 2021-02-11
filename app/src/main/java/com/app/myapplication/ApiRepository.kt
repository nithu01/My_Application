package com.app.myapplication

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiRepository  {

    var iRetrofit :IRetrofit? = null

//    fun getcoindata() : MutableLiveData<Data>{
//
//        val mutableLiveData = MutableLiveData<Data>()
//        val iRetrofit:IRetrofit = Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build().create(IRetrofit::class.java)
////        val crypto: Observable<List<Data>> = iRetrofit.getconin("btc").map {
////            result -> Observable.fromIterable(result.ticker.markets))
////            .flatMap(x -> x).filter(y -> {
////                y.coinName = "btc";
////                return true;
////            }).toList().toObservable();
//        }
//        return mutableLiveData
//    }
//    fun offer(): MutableLiveData<OfferResponse?>? {
//        val mutableLiveData = MutableLiveData<OfferResponse?>()
//        val retrofit = Retrofit.Builder().baseUrl("https://www.selfmandi.com/mobileapi/").addConverterFactory(GsonConverterFactory.create()).build()
//        apiinterface = retrofit.create(Apiinterface::class.java)
//        val call: Call<OfferResponse> = apiinterface.offer("RTaaAHhswvvXwALXhoNBgtyyHBBGISK")
//        call.enqueue(object : Callback<OfferResponse?> {
//            override fun onResponse(call: Call<OfferResponse?>, response: Response<OfferResponse?>) {
//                mutableLiveData.setValue(response.body())
//            }
//
//            override fun onFailure(call: Call<OfferResponse?>, t: Throwable) {}
//        })
//        return mutableLiveData
//    }

    fun getoperator() :MutableLiveData<ResponseX>{
        val mutableLiveData = MutableLiveData<ResponseX>()
        val retrofit = Retrofit.Builder().baseUrl("https://www.samratpay.com/agentapi/").addConverterFactory(GsonConverterFactory.create()).build()
        val apiinterface = retrofit.create(IRetrofit::class.java)
        val callback :Call<ResponseX> = apiinterface.getoperator()
        callback.enqueue( object : Callback<ResponseX>{
            override fun onFailure(call: Call<ResponseX>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<ResponseX>, response: Response<ResponseX>) {
                mutableLiveData.value = response.body()
            }

        })
        return mutableLiveData
    }
    fun getdata():MutableLiveData<PremiumRetailer>{
//        var gson = GsonBuilder().setLenient().create()
        val mutableLiveData=MutableLiveData<PremiumRetailer>()
        val retrofit = Retrofit.Builder().baseUrl("https://www.voltemart.com/mobileapi/").addConverterFactory(GsonConverterFactory.create()).build()
        val apiinterface = retrofit.create(IRetrofit::class.java)
        val call:Call<PremiumRetailer> = apiinterface.getsata()
        call.enqueue( object : Callback<PremiumRetailer>{
            override fun onFailure(call: Call<PremiumRetailer>, t: Throwable) {
                Log.d("TAG","VALUEDATA"+t)
            }

            override fun onResponse(call: Call<PremiumRetailer>, response: Response<PremiumRetailer>) {
                mutableLiveData.value=response.body()
            }
        })
        return mutableLiveData
    }
}