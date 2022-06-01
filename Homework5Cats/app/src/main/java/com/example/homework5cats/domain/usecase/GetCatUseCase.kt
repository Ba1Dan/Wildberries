package com.example.homework5cats.domain.usecase

import com.example.homework5cats.data.model.Cat
import com.example.homework5cats.data.model.ResultModel
import com.example.homework5cats.domain.repository.CatsRepository

class GetCatUseCase(private val catsRepository: CatsRepository) {

    suspend fun execute(): ResultModel<List<Cat>> {
        return catsRepository.getCat()
    }
}