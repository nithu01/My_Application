package com.app.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers.io


class MainActivity5 : AppCompatActivity() {

    var spinner: Spinner?= null
    var apiRepository : ApiRepository ? =null
    var arrayList: ArrayList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)
        init()
        getoperator()
    }
    fun init(){
        spinner=findViewById(R.id.spinner)
        apiRepository = ApiRepository()
    }
    fun getoperator(){
        apiRepository!!.getoperator().observe( this , Observer { type->
                for( i in 0 until  type.data.size){
                    arrayList.add(type.data.get(i).operator)
                }
            val arrayAdapter : ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,arrayList)
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1)
            spinner!!.adapter= arrayAdapter
        })

    }


}

