package com.example.homework7cats.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.homework7cats.data.db.entity.FavouriteCatEntity

@Database(entities = [FavouriteCatEntity::class], version = 1, exportSchema = false)
@TypeConverters(CatTypeConverter::class)
abstract class CatsDatabase : RoomDatabase() {

    abstract fun favouriteCatsDao(): FavouriteCatsDao

    companion object {
        private const val DATABASE_NAME = "cats_database"

        fun create(context: Context) =   Room.databaseBuilder(
            context,
            CatsDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}