package com.example.homework5dota.domain.repository

import com.example.homework5dota.data.model.Hero
import com.example.homework5dota.data.model.ResultModel

interface HeroesRepository {

    suspend fun getHeroes(): ResultModel<List<Hero>>
}