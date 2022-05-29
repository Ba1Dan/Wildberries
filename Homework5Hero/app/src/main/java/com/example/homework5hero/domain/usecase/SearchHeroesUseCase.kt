package com.example.homework5hero.domain.usecase

import com.example.homework5hero.data.model.Hero
import com.example.homework5hero.domain.repository.HeroesRepository

class SearchHeroesUseCase(private val heroesRepository: HeroesRepository) {

    suspend fun execute(query: String): List<Hero> {
        return heroesRepository.searchHeroes(query)
    }
}