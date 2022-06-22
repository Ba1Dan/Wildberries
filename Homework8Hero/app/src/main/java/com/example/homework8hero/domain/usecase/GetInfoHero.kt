package com.example.homework8hero.domain.usecase

import com.example.homework8hero.data.model.HeroDetail
import com.example.homework8hero.domain.repository.HeroesRepository
import javax.inject.Inject

class GetInfoHero @Inject constructor(private val heroesRepository: HeroesRepository) {

    suspend fun execute(id: Int): HeroDetail {
        return heroesRepository.getInfoHeroById(id)
    }
}