package com.example.homework5cats.domain.usecase

import com.example.homework5cats.data.model.FavouriteCat
import com.example.homework5cats.data.model.ResultModel
import com.example.homework5cats.domain.repository.CatsRepository

class GetFavouriteCatsUseCase(private val catsRepository: CatsRepository) {

    suspend fun execute(): ResultModel<List<FavouriteCat>> {
        return catsRepository.getFavouriteCats()
    }
}