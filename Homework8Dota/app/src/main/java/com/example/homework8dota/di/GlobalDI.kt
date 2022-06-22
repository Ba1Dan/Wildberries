package com.example.homework8dota.di

import android.content.Context
import com.example.homework8dota.data.db.FileManager
import com.example.homework8dota.data.db.LocalDataSource
import com.example.homework8dota.data.model.Hero
import com.example.homework8dota.data.network.RemoteDataSource
import com.example.homework8dota.data.network.RequestManager
import com.example.homework8dota.data.repository.HeroesRepositoryImpl
import com.example.homework8dota.domain.usecase.GetHeroesFromFileUseCase
import com.example.homework8dota.domain.usecase.GetHeroesUseCase
import com.example.homework8dota.presentation.ui.list.HeroesViewModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Types

class GlobalDI private constructor(private val context: Context) {

    private val type by lazy {
        Types.newParameterizedType(
            MutableList::class.java,
            Hero::class.java
        )
    }

    private val adapter: JsonAdapter<List<Hero>> by lazy {
        RequestManager.createMoshi().adapter(type)
    }

    private val fileManager by lazy { FileManager(context) }
    private val localDataSource by lazy { LocalDataSource(fileManager) }
    private val remoteDatsSource by lazy { RemoteDataSource(RequestManager.createClient()) }

    private val repository by lazy {
        HeroesRepositoryImpl(
            remoteDatsSource,
            adapter,
            localDataSource
        )
    }

    private val getHeroesUseCase by lazy { GetHeroesUseCase(repository) }
    private val getHeroesFromFileUseCase by lazy { GetHeroesFromFileUseCase(repository) }

    val heroesViewModel by lazy {
        HeroesViewModel(
            getHeroesUseCase = getHeroesUseCase,
            getHeroesFromFileUseCase = getHeroesFromFileUseCase
        )
    }

    companion object {

        lateinit var INSTANCE: GlobalDI

        fun init(context: Context) {
            INSTANCE = GlobalDI(context)
        }
    }
}