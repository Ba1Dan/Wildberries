package com.example.homework5hero.domain.usecase

import com.example.homework5hero.data.model.HeroDetail
import com.example.homework5hero.domain.repository.HeroesRepository

class GetInfoHero(private val heroesRepository: HeroesRepository) {

    suspend fun execute(id: Int): HeroDetail {
        return heroesRepository.getInfoHeroById(id)
    }
}