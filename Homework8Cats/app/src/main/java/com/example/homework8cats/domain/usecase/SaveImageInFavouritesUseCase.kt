package com.example.homework8cats.domain.usecase

import com.example.homework8cats.data.model.FavouriteModel
import com.example.homework8cats.data.model.ResponseModel
import com.example.homework8cats.domain.repository.CatsRepository
import javax.inject.Inject

class SaveImageInFavouritesUseCase @Inject constructor(private val catsRepository: CatsRepository) {

    suspend fun execute(favourite: FavouriteModel): ResponseModel? {
        return catsRepository.saveImageInFavourites(favourite)
    }
}