package com.app.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqliteHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {
   companion object{
       var DATABASE_VERSION = 1
       var DATABASE_NAME= "EMPLOYEE_DATA"
       var DATABASE_TABLE="EMPLOYEE"
       var KEY_ID="ID"
       var KEY_NAME="NAME"
       var KEY_AGE="age"
   }

    override fun onCreate(db: SQLiteDatabase?) {
    val CREATE_TABLE=("CREATE TABLE "+DATABASE_TABLE+"("+ KEY_ID+" PRIMARY KEY,"+ KEY_NAME+" TEXT,"+ KEY_AGE+" TEXT"+")")
    db!!.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE)
        onCreate(db)
    }

    fun insert(id:String,name:String,age:String){
        var sqliteDatabase =writableDatabase
        var contentValues = ContentValues()
        contentValues.put("ID",id)
        contentValues.put("NAME",name)
        contentValues.put("AGE",age)
        sqliteDatabase.insert(DATABASE_TABLE,null,contentValues)

    }
    fun getdata() : ArrayList<Employeedata>{
        var sqliteDatabase = readableDatabase
        var arrayList:ArrayList<Employeedata> = ArrayList()
        var qry = "Select * from EMPLOYEE"
        var cursor : Cursor =sqliteDatabase.rawQuery(qry,null)
        if(cursor.moveToFirst()){
            while(cursor.moveToNext()){
                var employeedata =Employeedata(id = cursor.getString(cursor.getColumnIndex("ID")),name = cursor.getString(cursor.getColumnIndex("NAME")),age=cursor.getString(cursor.getColumnIndex("age")))
                arrayList.add(employeedata)
            }
        }
        return  arrayList
        }

    fun deletesingle(id:String): Int{
        var sqliteDatabase = writableDatabase
        var whereclause : Array<String> = arrayOf<String>(id)
        var count: Int = sqliteDatabase.delete(DATABASE_TABLE, KEY_ID+"= ?",whereclause)
        return count
    }

    fun update(id:String,name:String) : Int{
        var sqliteDatabase = writableDatabase
        var whereclause :Array<String> = arrayOf<String>(id)
        var contentValues = ContentValues()
        contentValues.put(KEY_NAME,name)
        var count: Int =sqliteDatabase.update(DATABASE_TABLE,contentValues, KEY_ID+"= ?",whereclause)
        return count
    }

    fun delete() :Boolean{
        var sqliteDatabase =  writableDatabase
        return (sqliteDatabase.delete(DATABASE_TABLE,null,null))>0

    }
}