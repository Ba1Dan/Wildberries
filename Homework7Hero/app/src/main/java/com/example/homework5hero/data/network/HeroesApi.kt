package com.example.homework5hero.data.network

import com.example.homework5hero.data.model.Hero
import com.example.homework5hero.data.model.HeroDetail
import com.example.homework5hero.data.model.ResultObject
import com.example.homework5hero.data.network.RequestManager.ACCESS_TOKEN
import retrofit2.http.GET
import retrofit2.http.Path

interface HeroesApi {

    @GET("all.json")
    suspend fun searchHeroes(): List<Hero>

    @GET("id/{heroId}.json")
    suspend fun getInfoHero(@Path("heroId") id: Int): HeroDetail
}