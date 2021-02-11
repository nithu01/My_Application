package com.app.myapplication;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class HomeViewModel extends ViewModel  {

    HomeRepository homeRepository;

    public HomeViewModel() {
        homeRepository=new HomeRepository();
    }

    public MutableLiveData<OfferResponse> getoffer(){
        return homeRepository.offer();
    }

    public MutableLiveData<List<Data>> getdata(){
        return homeRepository.getdata();
    }

    public MutableLiveData<List<Data>> getdataa(){
        return homeRepository.getdataa();
    }


}
