package com.app.myapplication

import android.bluetooth.BluetoothDevice
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.lang.reflect.Method


class RecyclerViewAdapter(context: Context, val list: ArrayList<Any>) : RecyclerView.Adapter<RecyclerViewAdapter.MyHolder>(){

    inner class MyHolder(itemview:View) : RecyclerView.ViewHolder(itemview){

        val txt_name: TextView =itemView.findViewById(R.id.device_name)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.activity_main,parent,false)
        return MyHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.txt_name.text= list.get(position).toString()
        holder.txt_name.setOnClickListener {
            createBond(holder.txt_name)
        }
    }

    @Throws(Exception::class)
    fun createBond(btDevice: TextView?): Boolean {
        val class1 = Class.forName("android.bluetooth.BluetoothDevice")
        val createBondMethod: Method = class1.getMethod("createBond")
        val returnValue = createBondMethod.invoke(btDevice) as Boolean
        return returnValue
    }

}


