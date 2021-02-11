package com.app.myapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CategoryTable::class ], version = 1)

public abstract class CategoryDatabase (): RoomDatabase() {
    abstract fun cartDAO(): CartDao?

    @Volatile
    private var cartDatabase: CategoryDatabase? = null


    open fun getCartDatabase(context: Context): CategoryDatabase? {
        if (cartDatabase == null) {
            cartDatabase = Room.databaseBuilder(context.applicationContext, CategoryDatabase::class.java, "CartV_Database").build()
        }
        return cartDatabase
    }

}