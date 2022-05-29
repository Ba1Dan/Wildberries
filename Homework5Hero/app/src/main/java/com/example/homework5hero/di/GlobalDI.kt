package com.example.homework5hero.di

import com.example.homework5hero.data.network.RequestManager
import com.example.homework5hero.data.repository.HeroesRepositoryImpl
import com.example.homework5hero.domain.usecase.GetInfoHero
import com.example.homework5hero.domain.usecase.SearchHeroesUseCase
import com.example.homework5hero.presentation.detail.DetailHeroViewModel
import com.example.homework5hero.presentation.list.ListHeroesViewModel

/**
 * Глобальный DI
 * происходит создание необходимых объектов
 * */
class GlobalDI private constructor() {

    private val repository by lazy { HeroesRepositoryImpl(RequestManager.service) }

    private val searchHeroesUseCase by lazy { SearchHeroesUseCase(repository) }
    val getInfoHero by lazy { GetInfoHero(repository) }

    val listHeroesViewModel by lazy {
        ListHeroesViewModel(
            searchHeroesUseCase = searchHeroesUseCase
        )
    }

    companion object {

        lateinit var INSTANCE: GlobalDI

        fun init() {
            INSTANCE = GlobalDI()
        }
    }
}