package com.app.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity7 : AppCompatActivity() , View.OnClickListener  {

    var id:EditText ? =null
    var name:EditText ? =null
    var age:EditText ? =null
    var update:Button ? =null
    var delete : Button?=null
    var save:Button ?=null
    var sqliteHelper : SqliteHelper ?=null
    var recyclerView : RecyclerView ?= null
    var arrayList:ArrayList<Employeedata>?=null
    var adapterr:Adapter?=null
    var arraylistt:ArrayList<Employeedata>?=null
    var compositeDisposable : CompositeDisposable ?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main7)
        init()
        setadapter()
        getdata()
        loginwithotp()
    }
    fun loginwithotp(){
        var jsonObject : JSONObject = JSONObject();
        jsonObject.put("mobile","9958737757")
        jsonObject.put("description","string")
        jsonObject.put("status","0")
        jsonObject.put("client_Reference_Id","string")
        jsonObject.put("user_Id","string")
        jsonObject.put("device_Id","string")
        jsonObject.put("login_Token","string")
        jsonObject.put("role_Id","0")
        jsonObject.put("fcM_Token","string")
        jsonObject.put("requested_By","2021-01-28T12:03:03.028Z")
        jsonObject.put("requested_On","2021-01-28T12:03:03.028Z")
        jsonObject.put("time_Zone_Id","2021-01-28T12:03:03.028Z")
        jsonObject.put("time_Zone_Region","2021-01-28T12:03:03.028Z")
        jsonObject.put("offset_Seconds","9958737757")
        jsonObject.put("longitude","9958737757")
        jsonObject.put("latitude","9958737757")
        jsonObject.put("address","9958737757")
        jsonObject.put("httpContext","{}")

        var retrofit = Retrofit.Builder().baseUrl("https://capi.saniologistics.com/").addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
        var iRetrofit =retrofit.create(IRetrofit::class.java)
        compositeDisposable!!.add(iRetrofit.loginwithotp(jsonObject.toString())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({response -> handleResponse(response)},{t->handleError(t)}))
    }
    fun handleResponse(responseX: ResponseX){
                     Toast.makeText(this@MainActivity7,""+responseX.Message,Toast.LENGTH_SHORT).show()
    }

    fun handleError(throwable: Throwable){

    }

    fun setadapter(){
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        adapterr= Adapter(this@MainActivity7,arrayList!!);
        recyclerView!!.adapter= adapterr
    }

    fun init(){
        compositeDisposable = CompositeDisposable();
        delete=findViewById(R.id.delete)
        update=findViewById(R.id.update)
        recyclerView =findViewById(R.id.recyclerview)
        sqliteHelper = SqliteHelper(this@MainActivity7);
        id=findViewById(R.id.id)
        name=findViewById(R.id.name)
        age=findViewById(R.id.age)
        save=findViewById(R.id.save)
        delete!!.setOnClickListener(this)
        update!!.setOnClickListener(this)
        save!!.setOnClickListener(this)
        arrayList= ArrayList()
        arraylistt=ArrayList()
    }

    override fun onClick(view: View?) {
      if(view==save){
                sqliteHelper!!.insert(id!!.text.toString(),name!!.text.toString(),age!!.text.toString())
                adapterr!!.notifyDataSetChanged()
                getdata()
      }
        if(view==delete){

//            if (sqliteHelper!!.delete()==true)
//            {
//                adapterr!!.notifyDataSetChanged()
//                Toast.makeText(this@MainActivity7,"Deleted",Toast.LENGTH_SHORT).show()
//            }else{
//                Toast.makeText(this@MainActivity7,""+sqliteHelper!!.delete(),Toast.LENGTH_SHORT).show()
//            }
            var count :Int=sqliteHelper!!.deletesingle(id!!.text.toString())
            Toast.makeText(this@MainActivity7,""+count,Toast.LENGTH_SHORT).show()
        }
        if(view==update){
            var count :Int=sqliteHelper!!.update(id!!.text.toString(),name!!.text.toString())
            Toast.makeText(this@MainActivity7,""+count,Toast.LENGTH_SHORT).show()
        }
    }

    fun getdata(){
            arraylistt = sqliteHelper!!.getdata();
//            for(value------- in arrayList!!){
//
//                Toast.makeText(this@MainActivity7,""+value,Toast.LENGTH_SHORT).show()
//            }
        arrayList!!.addAll(arraylistt!!)
        adapterr!!.notifyDataSetChanged()
    }
}

