package com.example.homework5cats.domain.repository

import com.example.homework5cats.data.model.*

interface CatsRepository {

    suspend fun getCat(): ResultModel<List<Cat>>

    suspend fun saveImageInFavourites(favourite: FavouriteModel): ResponseModel?

    suspend fun getFavouriteCats(): ResultModel<List<FavouriteCat>>
}