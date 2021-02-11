package com.app.myapplication

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity2 : AppCompatActivity(), View.OnClickListener {

    var btn_turnon:Button?=null
    var btn_list:Button?=null
    var btn_turnoff:Button?=null
    var btn_turnvisible:Button?=null
    var BA:BluetoothAdapter?=null
    var pairdevices:Set<BluetoothDevice> ?=null
    var listView:RecyclerView?=null
    val list = ArrayList<Any>()
    val newdevices = ArrayList<Device>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        btn_turnon=findViewById(R.id.button) as Button
        btn_list=findViewById(R.id.button3) as Button
        btn_turnvisible=findViewById(R.id.button2) as Button
        btn_turnoff=findViewById(R.id.button4) as Button
        BA= BluetoothAdapter.getDefaultAdapter();
        listView=findViewById(R.id.listView)

        listView!!.layoutManager=LinearLayoutManager(this)
        btn_turnon!!.setOnClickListener(this)
        btn_turnoff!!.setOnClickListener(this)
        btn_turnvisible!!.setOnClickListener(this)
        btn_list!!.setOnClickListener(this)
//        listView!!.setAdapter(RecyclerViewAdapter(0, object : AdapterView.OnItemClickListener() {
//            fun onItemClick(item: ContentItem?) {
//                Toast.makeText(this@MainActivity2, "Item Clicked", Toast.LENGTH_LONG).show()
//            }
//        }))
//        val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
//        registerReceiver(bReceiver,filter)
    }

//    override fun onStart() {
//        super.onStart()
//        var filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
////         filter = IntentFilter(BluetoothDevice.STA)
////
////        filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
//        registerReceiver(bReceiver,filter)
//    }
    override fun onClick(v: View?) {
        if(v==btn_turnon){
            if(!BA!!.isEnabled)
            {
                val intent= Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(intent,0);
                Toast.makeText(getApplicationContext(), "Turned on",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(), "Already on",Toast.LENGTH_LONG).show();
            }

        }
        if(v==btn_list){

//            if(BA!!.isDiscovering)
//            {
//                BA!!.cancelDiscovery()
                BA!!.startDiscovery()
                val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
                registerReceiver(bReceiver,filter)

//            }

//            IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
//            registerReceiver(bReciever, filter);
//            The Receiver…

}

//            pairdevices = BA!!.bondedDevices;
//            val list = ArrayList<Any>()
//            Toast.makeText(applicationContext, "Showing Paired Devices"+pairdevices!!.size, Toast.LENGTH_SHORT).show()
//
//            for (bt in pairdevices!!)
//            {
//                list.add(bt.name)
//                Toast.makeText(applicationContext, "Showing Paired Devices", Toast.LENGTH_SHORT).show()
//
//            }
//
//
        //}
        if(v==btn_turnvisible){
            val getVisible=Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE)
            startActivityForResult(getVisible,0)
        }
        if(v==btn_turnoff){
            BA!!.disable()
            Toast.makeText(getApplicationContext(), "Turned off",Toast.LENGTH_LONG).show();

        }
    }
    val  bReceiver : BroadcastReceiver = object : BroadcastReceiver(){

        override fun onReceive(context: Context?, intent: Intent?) {


            val action: String? = intent!!.action
            when (action) {
                BluetoothDevice.ACTION_FOUND -> {
                    // Discovery has found a device. Get the BluetoothDevice
                    // object and its info from the Intent.
                    val device: BluetoothDevice? = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                    if(device!=null){
                        list.add(device!!.name)
//                        newdevices.addAll(list)
                        showList()
                    }


                }
            }
        }
    }
    fun showList(){
        val adapter:RecyclerViewAdapter= RecyclerViewAdapter(this@MainActivity2,list);
//        val adapter: ArrayAdapter<*> = ArrayAdapter(this@MainActivity2, android.R.layout.simple_list_item_1, list)
        listView!!.setAdapter(adapter)
    }
}

