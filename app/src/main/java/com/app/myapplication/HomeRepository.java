package com.app.myapplication;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeRepository {
    Apiinterface apiinterface;
    MutableLiveData<List<Data>> mutableLiveData=new MutableLiveData<>();
    public MutableLiveData<OfferResponse> offer(){
        MutableLiveData<OfferResponse> mutableLiveData=new MutableLiveData<>();
        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://www.selfmandi.com/mobileapi/").addConverterFactory(GsonConverterFactory.create()).build();
        apiinterface=retrofit.create(Apiinterface.class);
        Call<OfferResponse> call=apiinterface.offer("RTaaAHhswvvXwALXhoNBgtyyHBBGISK");
        call.enqueue(new Callback<OfferResponse>() {
            @Override
            public void onResponse(Call<OfferResponse> call, Response<OfferResponse> response) {
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<OfferResponse> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<List<Data>> getdata(){
        Log.d("TAG","userdatas");

        MutableLiveData<List<Data>> mutableLiveData=new MutableLiveData<>();
        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://uniqueandrocode.000webhostapp.com/hiren/").addConverterFactory(GsonConverterFactory.create()).build();
        apiinterface=retrofit.create(Apiinterface.class);
        Call<List<Data>> call=apiinterface.getdata();
        call.enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
//                List<Data> arrayList=new ArrayList<>();
//                arrayList=response.body();
                Log.d("TAG","userdata"+response.body());
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {
                Log.d("TAG","userdata"+t);
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<List<Data>> getdataa(){
        Log.d("TAG","userdatas");
//
//        MutableLiveData<Data> mutableLiveData=new MutableLiveData<>();

        IRetrofit apiinterface=new Retrofit.Builder().baseUrl("https://uniqueandrocode.000webhostapp.com/hiren/").addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build().create(IRetrofit.class);
//        IRetrofit apiinterface=retrofit.create(IRetrofit.class);
//        Call<List<Data>> call=apiinterface.getdata();
//        call.enqueue(new Callback<List<Data>>() {
//            @Override
//            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
////                List<Data> arrayList=new ArrayList<>();
////                arrayList=response.body();
//                Log.d("TAG","userdata"+response.body());
//                mutableLiveData.setValue(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<List<Data>> call, Throwable t) {
//                Log.d("TAG","userdata"+t);
//            }
//        });

        CompositeDisposable compositeDisposable=new CompositeDisposable();
        compositeDisposable.add(apiinterface.getdata()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse,this::handleError));

        return mutableLiveData;

    }

    private void handleResponse(List<Data> data) {
        mutableLiveData.setValue(data);
    }

    public void handleError(Throwable throwable){
        Log.d("TAG","userdata"+throwable);
        //Toast.makeText(this, "Error "+throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }

}
