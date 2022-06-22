package com.example.homework7hero.di

import com.example.homework7hero.data.repository.HeroesRepositoryImpl
import com.example.homework7hero.domain.repository.HeroesRepository
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    fun bindProfileRepository(impl: HeroesRepositoryImpl): HeroesRepository
}