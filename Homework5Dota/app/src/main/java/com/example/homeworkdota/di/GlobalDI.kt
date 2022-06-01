package com.example.homeworkdota.di

import com.example.homeworkdota.data.network.RemoteDataSource
import com.example.homeworkdota.data.network.RequestManager
import com.example.homeworkdota.data.repository.HeroesRepositoryImpl
import com.example.homeworkdota.domain.usecase.GetHeroesUseCase
import com.example.homeworkdota.presentation.ui.list.HeroesViewModel

class GlobalDI private constructor() {

    private val remoteDatsSource by lazy { RemoteDataSource(RequestManager.createClient()) }
    private val repository by lazy { HeroesRepositoryImpl(remoteDatsSource, RequestManager.createMoshi()) }

    private val getHeroesUseCase by lazy { GetHeroesUseCase(repository) }

    val heroesViewModel by lazy {
        HeroesViewModel(
            getHeroesUseCase = getHeroesUseCase,
        )
    }

    companion object {

        lateinit var INSTANCE: GlobalDI

        fun init() {
            INSTANCE = GlobalDI()
        }
    }
}