package com.example.homework8dota.domain.usecase

import com.example.homework8dota.data.model.Hero
import com.example.homework8dota.domain.repository.HeroesRepository

class GetHeroesFromFileUseCase(private val heroesRepository: HeroesRepository) {

    suspend fun execute(): List<Hero> {
        return heroesRepository.readDataFromFile()
    }
}