package com.example.homework7dota.domain.repository

import com.example.homework7dota.data.model.Hero
import com.example.homework7dota.data.model.ResultModel

interface HeroesRepository {

    suspend fun getHeroes(): ResultModel<List<Hero>>

    suspend fun readDataFromFile(): List<Hero>
}