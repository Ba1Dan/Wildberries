package com.example.homework8cats.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.homework8cats.data.db.FavouriteCatsDao
import com.example.homework8cats.data.db.entity.FavouriteCatEntity
import com.example.homework8cats.data.model.FavouriteCat

class LocalDataSource(
    private val favouriteCatsDao: FavouriteCatsDao
) {

    suspend fun saveFavouriteCats(cats: List<FavouriteCat>) {
        favouriteCatsDao.save(cats.map { cat -> FavouriteCatEntity(cat.id, cat.image) })
    }

    suspend fun deleteAllFavouriteCats() {
        favouriteCatsDao.delete()
    }

    fun getFavouritesCats(): LiveData<List<FavouriteCat>> {
        val catsLiveData = favouriteCatsDao.read()
        return Transformations.map(catsLiveData) { list ->
            list.map { cat -> FavouriteCat(cat.id, cat.image) }
        }
    }
}