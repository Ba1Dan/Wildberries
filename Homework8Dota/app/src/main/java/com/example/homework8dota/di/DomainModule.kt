package com.example.homework8dota.di

import com.example.homework8dota.data.repository.HeroesRepositoryImpl
import com.example.homework8dota.domain.repository.HeroesRepository
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    fun bindProfileRepository(impl: HeroesRepositoryImpl): HeroesRepository
}