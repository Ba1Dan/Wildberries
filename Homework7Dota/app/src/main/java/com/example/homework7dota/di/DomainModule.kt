package com.example.homework7dota.di

import com.example.homework7dota.data.repository.HeroesRepositoryImpl
import com.example.homework7dota.domain.repository.HeroesRepository
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    fun bindProfileRepository(impl: HeroesRepositoryImpl): HeroesRepository
}