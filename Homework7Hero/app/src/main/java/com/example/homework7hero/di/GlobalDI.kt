package com.example.homework7hero.di

import android.content.Context
import com.example.homework7hero.data.db.LocalDataSource
import com.example.homework7hero.data.db.SharedPreferencesManager
import com.example.homework7hero.data.network.RequestManager
import com.example.homework7hero.data.repository.HeroesRepositoryImpl
import com.example.homework7hero.domain.usecase.GetInfoHero
import com.example.homework7hero.domain.usecase.SearchHeroesUseCase
import com.example.homework7hero.presentation.detail.DetailHeroViewModel
import com.example.homework7hero.presentation.list.ListHeroesViewModel

/**
 * Глобальный DI
 * происходит создание необходимых объектов
 * */
class GlobalDI private constructor(private val context: Context) {

    private val sharedPreferencesManager by lazy { SharedPreferencesManager(context) }
    private val localDataSource by lazy { LocalDataSource(sharedPreferencesManager) }
    private val repository by lazy { HeroesRepositoryImpl(RequestManager.service, localDataSource) }

    val getInfoHero by lazy { GetInfoHero(repository) }

    val listHeroesViewModel by lazy {
        ListHeroesViewModel(
            repository
        )
    }

    companion object {

        lateinit var INSTANCE: GlobalDI

        fun init(context: Context) {
            INSTANCE = GlobalDI(context)
        }
    }
}