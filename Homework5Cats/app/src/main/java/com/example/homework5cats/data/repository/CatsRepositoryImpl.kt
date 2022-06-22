package com.example.homework5cats.data.repository

import com.example.homework5cats.data.RemoteDatsSource
import com.example.homework5cats.data.model.*
import com.example.homework5cats.data.network.ApiService
import com.example.homework5cats.domain.repository.CatsRepository
import javax.inject.Inject

class CatsRepositoryImpl @Inject constructor(private val remoteDatsSource: RemoteDatsSource) : CatsRepository {

    override suspend fun getCat(): ResultModel<List<Cat>> {
        return remoteDatsSource.getCat()
    }

    override suspend fun saveImageInFavourites(favourite: FavouriteModel): ResponseModel? {
        return remoteDatsSource.saveImageInFavourites(favourite)
    }

    override suspend fun getFavouriteCats(): ResultModel<List<FavouriteCat>> {
        return remoteDatsSource.getFavouriteCats()
    }
}