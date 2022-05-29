package com.example.homework5hero.data.repository

import com.example.homework5hero.data.model.Hero
import com.example.homework5hero.data.model.HeroDetail
import com.example.homework5hero.data.network.HeroesApi
import com.example.homework5hero.domain.repository.HeroesRepository

class HeroesRepositoryImpl(private val heroesApi: HeroesApi) : HeroesRepository {

    override suspend fun searchHeroes(query: String): List<Hero> {
        return heroesApi.searchHeroes(query).result
    }

    override suspend fun getInfoHeroById(id: Int): HeroDetail {
        return heroesApi.getInfoHero(id)
    }
}