package com.example.homework8hero.di

import com.example.homework8hero.data.repository.HeroesRepositoryImpl
import com.example.homework8hero.domain.repository.HeroesRepository
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    fun bindProfileRepository(impl: HeroesRepositoryImpl): HeroesRepository
}