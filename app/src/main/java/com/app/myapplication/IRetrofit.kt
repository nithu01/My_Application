package com.app.myapplication

import io.reactivex.Observable
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

interface IRetrofit {

    @POST("/api/MobileLogin/MobileOTP")
    fun loginwithotp(@Body data:String) : Observable<ResponseX>

    @Headers("apicode:YNOVqgrBE7wvvXwALXhoNhVMAZKiDLWE")
    @POST("login")
    fun login(@Query("username")username:String,@Query("password")password:String) : Call<User>

    @GET("androidwebmvvm.php")
    fun getdata():Observable<List<Data>>

    @GET("{coin}-usd")
    fun getconin(@Path("coin")coin:String):Observable<Data>

    @Headers("apicode:YNOVqgrBE7wvvXwALXhoNhVMAZKiDLWE")
    @POST
    fun gettype():Call<OperaterModal>

    @Headers("Apicode:YNOVqgrBE7wvvXwALXhoNhVMAZKiDLWE")
    @POST("getOperatorsList")
    fun getoperator():Call<ResponseX>

    @Headers("apicode:RTaaAHhswvvXwALXhoNBgtyyHBBGISK")
    @POST("getcategories")
    fun getsata():Call<PremiumRetailer>

}