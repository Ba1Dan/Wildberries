package com.example.homework8dota.domain.usecase

import com.example.homework8dota.data.model.Hero
import com.example.homework8dota.data.model.ResultModel
import com.example.homework8dota.domain.repository.HeroesRepository
import javax.inject.Inject

class GetHeroesUseCase @Inject constructor(private val heroesRepository: HeroesRepository) {

    suspend fun execute(): ResultModel<List<Hero>> {
        return heroesRepository.getHeroes()
    }
}