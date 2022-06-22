package com.example.homework8cats.data

import com.example.homework8cats.data.model.*
import com.example.homework8cats.data.network.ApiService

class RemoteDatsSource(private val apiService: ApiService) {

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