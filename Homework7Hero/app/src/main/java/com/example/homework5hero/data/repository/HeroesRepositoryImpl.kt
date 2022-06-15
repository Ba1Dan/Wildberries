package com.example.homework5hero.data.repository

import com.example.homework5hero.data.db.LocalDataSource
import com.example.homework5hero.data.model.Hero
import com.example.homework5hero.data.model.HeroDetail
import com.example.homework5hero.data.network.HeroesApi
import com.example.homework5hero.domain.repository.HeroesRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class HeroesRepositoryImpl(private val heroesApi: HeroesApi, private val localDataSource: LocalDataSource) : HeroesRepository {

    override suspend fun searchHeroes(query: String): List<Hero> {
        val result: List<Hero> = heroesApi.searchHeroes()
        val json = Gson().toJson(result)
        localDataSource.saveHeroes(json)
        return result
    }

    override suspend fun getInfoHeroById(id: Int): HeroDetail {
        return heroesApi.getInfoHero(id)
    }

    override suspend fun getHeroesFromLocal(): List<Hero> {
        val heroesAsString = localDataSource.getHeroes()
        val gson = Gson()
        val type = object : TypeToken<List<Hero>>() {}.type
        return gson.fromJson(heroesAsString, type)
    }
}