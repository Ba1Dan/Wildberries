package com.example.homework7cats.domain.usecase

import com.example.homework7cats.data.model.Cat
import com.example.homework7cats.data.model.ResultModel
import com.example.homework7cats.domain.repository.CatsRepository
import javax.inject.Inject

class GetCatUseCase @Inject constructor(private val catsRepository: CatsRepository) {

    suspend fun execute(): ResultModel<List<Cat>> {
        return catsRepository.getCat()
    }
}