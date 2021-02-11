package com.app.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import kotlin.collections.ArrayList

class MainActivity4 : AppCompatActivity() {

    var recyclerview: RecyclerView?=null
    var adapterr:Adapter?=null
    var arraylist:ArrayList<Employeedata>?=null
    var compositeDisposable:CompositeDisposable?=null
    var apiModel : ApiModel ?=null
    var calendar = Calendar.getInstance()
    var month = 0
    var year:Int = 0
    var sqliteDatabase :SqliteDatabase ?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
        init();
        month = calendar.get(java.util.Calendar.YEAR)
        year = calendar.get(java.util.Calendar.MONTH)
        getData()
      //  showdata()
        /*update*/
//        var value:Boolean=sqliteDatabase!!.update("Gifts","4")
//        Toast.makeText(this@MainActivity4,""+value,Toast.LENGTH_SHORT).show()
        /*delete*/
//        var value:Boolean=sqliteDatabase!!.delete("5")
//        Toast.makeText(this@MainActivity4,""+value,Toast.LENGTH_SHORT).show()
    }

    fun init(){
        sqliteDatabase = SqliteDatabase(this)
        arraylist=ArrayList()
        recyclerview=findViewById(R.id.recyclerview)
        recyclerview!!.layoutManager=LinearLayoutManager(this)
        adapterr= Adapter(this@MainActivity4,arraylist!!);
        recyclerview!!.adapter=adapterr
        apiModel= ViewModelProviders.of(this).get(ApiModel::class.java)

    }

    fun getData(){
        apiModel!!.getdata().observe(this,Observer<PremiumRetailer>{ data->
            for(i in 0 until data.data.size)
            {
               //  sqliteDatabase!!.insertdata(data.data.get(i).categoryid,data.data.get(i).categoryname,data.data.get(i).categoryimage)
            }

        })
    }
//    fun showdata(){
//        val arrayLists : ArrayList<Categorydata> = sqliteDatabase!!.select()
//
////        for(value in arrayList){
////            arrayList.add(value)
////
////            //Toast.makeText(this@MainActivity4,""+arrayList.size,Toast.LENGTH_SHORT).show()
////        }
//
//        arraylist!!.addAll(arrayLists)
//        adapterr!!.notifyDataSetChanged()
//    }

}