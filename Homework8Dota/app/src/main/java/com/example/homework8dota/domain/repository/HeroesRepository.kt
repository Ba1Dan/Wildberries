package com.example.homework8dota.domain.repository

import com.example.homework8dota.data.model.Hero
import com.example.homework8dota.data.model.ResultModel

interface HeroesRepository {

    suspend fun getHeroes(): ResultModel<List<Hero>>

    suspend fun readDataFromFile(): List<Hero>
}