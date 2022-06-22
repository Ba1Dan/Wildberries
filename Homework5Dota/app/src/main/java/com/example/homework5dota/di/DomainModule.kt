package com.example.homework5dota.di

import com.example.homework5dota.data.repository.HeroesRepositoryImpl
import com.example.homework5dota.domain.repository.HeroesRepository
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    fun bindProfileRepository(impl: HeroesRepositoryImpl): HeroesRepository
}