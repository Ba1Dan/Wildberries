package com.example.homeworkdota.domain.usecase

import com.example.homeworkdota.data.model.Hero
import com.example.homeworkdota.data.model.ResultModel
import com.example.homeworkdota.domain.repository.HeroesRepository

class GetHeroesUseCase(private val heroesRepository: HeroesRepository) {

    suspend fun execute(): ResultModel<List<Hero>> {
        return heroesRepository.getHeroes()
    }
}