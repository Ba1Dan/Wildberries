package com.example.homework7cats.data.repository

import androidx.lifecycle.LiveData
import com.example.homework7cats.data.LocalDataSource
import com.example.homework7cats.data.RemoteDatsSource
import com.example.homework7cats.data.model.*
import com.example.homework7cats.domain.repository.CatsRepository
import javax.inject.Inject

class CatsRepositoryImpl @Inject constructor(
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