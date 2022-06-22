package com.example.homework7hero.domain.usecase

import com.example.homework7hero.data.model.HeroDetail
import com.example.homework7hero.domain.repository.HeroesRepository
import javax.inject.Inject

class GetInfoHero @Inject constructor(private val heroesRepository: HeroesRepository) {

    suspend fun execute(id: Int): HeroDetail {
        return heroesRepository.getInfoHeroById(id)
    }
}