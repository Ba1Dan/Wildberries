package com.example.homework8dota.domain.usecase

import com.example.homework8dota.data.model.Hero
import com.example.homework8dota.data.model.ResultModel
import com.example.homework8dota.domain.repository.HeroesRepository

class GetHeroesUseCase(private val heroesRepository: HeroesRepository) {

    suspend fun execute(): ResultModel<List<Hero>> {
        return heroesRepository.getHeroes()
    }
}