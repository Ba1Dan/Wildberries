package com.example.homeworkdota.domain.repository

import com.example.homeworkdota.data.model.Hero
import com.example.homeworkdota.data.model.ResultModel

interface HeroesRepository {

    suspend fun getHeroes(): ResultModel<List<Hero>>
}