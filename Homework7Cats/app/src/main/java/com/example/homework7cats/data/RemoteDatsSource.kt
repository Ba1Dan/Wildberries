package com.example.homework7cats.data

import com.example.homework7cats.data.model.*
import com.example.homework7cats.data.network.ApiService
import javax.inject.Inject

class RemoteDatsSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getCat(): ResultModel<List<Cat>> {
        return try {
            ResultModel.Success(apiService.getProducts())
        } catch (e: Exception) {
            ResultModel.Failure(e.message)
        }
    }

    suspend fun saveImageInFavourites(favourite: FavouriteModel): ResponseModel? {
        return apiService.saveImageInFavourites(favourite)
    }

    suspend fun getFavouriteCats(): ResultModel<List<FavouriteCat>> {
        return try {
            ResultModel.Success(apiService.getFavouriteCats())
        } catch (e: Exception) {
            ResultModel.Failure(e.message)
        }
    }
}