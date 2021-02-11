package com.app.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class Adapter (var context: Context, var arrayList: ArrayList<Employeedata>): RecyclerView.Adapter<Adapter.MyHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.item_row,parent,false)
        return MyHolder(v)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.text.text = arrayList.get(position).id
        holder.name.text = arrayList.get(position).name
//        Picasso.with(context).load("https://www.voltemart.com/uploads/products/"+arrayList.get(position).categoryimage).into(holder.img);

    }
    class MyHolder(v:View): RecyclerView.ViewHolder(v){
        val text : TextView = v.findViewById(R.id.id)
        val name : TextView = v.findViewById(R.id.name)
        val img : ImageView = v .findViewById(R.id.image)
    }
}