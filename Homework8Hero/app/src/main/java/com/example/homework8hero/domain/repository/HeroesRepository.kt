package com.example.homework8hero.domain.repository

import com.example.homework8hero.data.model.Hero
import com.example.homework8hero.data.model.HeroDetail

interface HeroesRepository {

    suspend fun searchHeroes(query: String): List<Hero>

    suspend fun getInfoHeroById(id: Int): HeroDetail

    suspend fun getHeroesFromLocal(): List<Hero>
}