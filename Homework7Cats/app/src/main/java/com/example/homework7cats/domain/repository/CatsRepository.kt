package com.example.homework7cats.domain.repository

import androidx.lifecycle.LiveData
import com.example.homework7cats.data.model.*

interface CatsRepository {

    suspend fun getCat(): ResultModel<List<Cat>>

    suspend fun saveImageInFavourites(favourite: FavouriteModel): ResponseModel?

    suspend fun getFavouriteCats(): ResultModel<List<FavouriteCat>>

    suspend fun saveFavouriteCats(cats: List<FavouriteCat>)

    suspend fun deleteAllFavouriteCats()

    fun getFavouritesFromDb(): LiveData<List<FavouriteCat>>
}