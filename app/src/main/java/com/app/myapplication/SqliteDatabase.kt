package com.app.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.StringBuilder

class SqliteDatabase(context: Context) : SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION)
{
    companion object{
        val DATABASE_VERSION=1
        val DATABASE_NAME="ITEMS"
        val TABLE_CONTACTS="CATEGORY"
        val KEY_ID="id"
        val KEY_NAME="name"
        val KEY_IMAGE="image"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE=("CREATE TABLE "+TABLE_CONTACTS+"("+KEY_ID+" PRIMARY KEY,"+KEY_NAME+" TEXT,"+KEY_IMAGE+" TEXT"+")")
        db!!.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS "+TABLE_CONTACTS)
        onCreate(db)
    }

    fun insertdata(id:String,name:String,img:String){
        val sqlitedatabase:SQLiteDatabase=writableDatabase
        val contentValues = ContentValues()
        contentValues.put("id",id)
        contentValues.put("name",name)
        contentValues.put("image",img)
        sqlitedatabase.insert(TABLE_CONTACTS,null,contentValues)
    }

    fun select() : ArrayList<Categorydata>{
       var arrayList : ArrayList<Categorydata> = ArrayList()
       var qry="Select * from CATEGORY"
       val sqliteDatabase : SQLiteDatabase = readableDatabase
       var cursor : Cursor = sqliteDatabase.rawQuery(qry,null)
       if(cursor.moveToFirst()){
           while (cursor.moveToNext()){
               var categorydata = Categorydata(categoryid = cursor.getString(cursor.getColumnIndex("id")),categoryname=cursor.getString(cursor.getColumnIndex("name")),categoryimage = cursor.getString(cursor.getColumnIndex("image")))
//               categorydata.categoryid=cursor.getInt(cursor.getColumnIndex("id"))
               arrayList.add(categorydata)
           }
       }
        return arrayList
    }

    fun update(name:String,id:String) : Boolean{
        var sqliteDatabase = readableDatabase
        var contentValues = ContentValues()
        contentValues.put("name",name)
        var stringbuilder : StringBuilder = StringBuilder()
        stringbuilder.append("id='")
        stringbuilder.append(id)
        stringbuilder.append("'")

        return (sqliteDatabase.update(TABLE_CONTACTS,contentValues,stringbuilder.toString(),null))>0


    }

    fun delete() : Boolean{
        var sqliteDatabase = writableDatabase
        return (sqliteDatabase.delete(TABLE_CONTACTS,null,null))>0
    }



}