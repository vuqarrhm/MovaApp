package com.example.film.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.film.model.BasketItems

@Dao
interface BasketDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(basket:BasketItems)

    @Query("Select * from basket")
    fun getAll():LiveData<List<BasketItems>>

    @Query("Delete from basket where id =:id")
    suspend fun delete(id:Int)
}