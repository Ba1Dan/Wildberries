package com.example.homework5cats.data.repository

import androidx.lifecycle.LiveData
import com.example.homework5cats.data.LocalDataSource
import com.example.homework5cats.data.RemoteDatsSource
import com.example.homework5cats.data.model.*
import com.example.homework5cats.data.network.ApiService
import com.example.homework5cats.domain.repository.CatsRepository

class CatsRepositoryImpl(
    private val remoteDatsSource: RemoteDatsSource,
    private val localDataSource: LocalDataSource
) : CatsRepository {

    override suspend fun getCat(): ResultModel<List<Cat>> {
        return remoteDatsSource.getCat()
    }

    override suspend fun saveImageInFavourites(favourite: FavouriteModel): ResponseModel? {
        return remoteDatsSource.saveImageInFavourites(favourite)
    }

    override suspend fun getFavouriteCats(): ResultModel<List<FavouriteCat>> {
        return remoteDatsSource.getFavouriteCats()
    }

    override suspend fun deleteAllFavouriteCats() {
        localDataSource.deleteAllFavouriteCats()
    }

    override suspend fun saveFavouriteCats(cats: List<FavouriteCat>) {
        localDataSource.saveFavouriteCats(cats)
    }

    override fun getFavouritesFromDb(): LiveData<List<FavouriteCat>> {
        return localDataSource.getFavouritesCats()
    }
}