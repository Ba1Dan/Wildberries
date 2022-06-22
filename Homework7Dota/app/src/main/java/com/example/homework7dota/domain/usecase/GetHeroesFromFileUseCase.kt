package com.example.homework7dota.domain.usecase

import com.example.homework7dota.data.model.Hero
import com.example.homework7dota.domain.repository.HeroesRepository
import javax.inject.Inject

class GetHeroesFromFileUseCase @Inject constructor(private val heroesRepository: HeroesRepository) {

    suspend fun execute(): List<Hero> {
        return heroesRepository.readDataFromFile()
    }
}