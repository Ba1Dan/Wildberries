package com.example.homework7cats.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.homework7cats.data.db.FavouriteCatsDao
import com.example.homework7cats.data.db.entity.FavouriteCatEntity
import com.example.homework7cats.data.model.FavouriteCat
import javax.inject.Inject

class LocalDataSource @Inject constructor(
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