package com.example.film.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import com.example.film.model.BasketItems

@Database(entities =[BasketItems::class], version = 8, exportSchema = false)
    abstract class AppDatabase:RoomDatabase(){
        abstract fun basketDao():BasketDAO
    }
