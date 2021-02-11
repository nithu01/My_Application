package com.app.myapplication;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Apiinterface {

    @POST("getoffers")
    Call<OfferResponse> offer(@Header("apicode")String apicode);

    @GET("androidwebmvvm.php")
    Call<List<Data>> getdata();

    @GET("airline-tickets.php")
    Single<List<Ticket>> searchTickets(@Query("from") String from, @Query("to") String to);

    @GET("airline-tickets-price.php")
    Single<Price> getPrice(@Query("flight_number") String flightNumber, @Query("from") String from, @Query("to") String to);

}
