package com.app.myapplication

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= "Category")
class CategoryTable{

    @PrimaryKey
    @NonNull
    @ColumnInfo(name ="categoryname")
    var categoryid: String?=null

    @ColumnInfo(name ="categoryname")
    var categoryname : String?=null;

    @ColumnInfo(name ="categoryimage")
    var categoryimage : String ?=null;

}