package com.example.homework7cats.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.homework7cats.data.db.entity.FavouriteCatEntity

@Dao
interface FavouriteCatsDao {

    @Insert
    suspend fun save(cats: List<FavouriteCatEntity>)

    @Query("DELETE FROM favourite_cats")
    suspend fun delete()

    @Query("SELECT * FROM favourite_cats")
    fun read(): LiveData<List<FavouriteCatEntity>>
}