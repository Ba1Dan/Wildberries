package com.example.homework7hero.domain.usecase

import com.example.homework7hero.data.model.Hero
import com.example.homework7hero.domain.repository.HeroesRepository

class SearchHeroesUseCase(private val heroesRepository: HeroesRepository) {

    suspend fun execute(query: String): List<Hero> {
        return heroesRepository.searchHeroes(query)
    }
}