package com.example.homeworkdota.domain.usecase

import com.example.homeworkdota.data.model.Hero
import com.example.homeworkdota.domain.repository.HeroesRepository

class GetHeroesFromFileUseCase(private val heroesRepository: HeroesRepository) {

    suspend fun execute(): List<Hero> {
        return heroesRepository.readDataFromFile()
    }
}