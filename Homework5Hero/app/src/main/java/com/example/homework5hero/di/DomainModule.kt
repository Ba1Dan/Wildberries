package com.example.homework5hero.di

import com.example.homework5hero.data.repository.HeroesRepositoryImpl
import com.example.homework5hero.domain.repository.HeroesRepository
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    fun bindProfileRepository(impl: HeroesRepositoryImpl): HeroesRepository
}