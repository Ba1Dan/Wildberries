package com.example.homework8cats.di

import com.example.homework8cats.data.repository.CatsRepositoryImpl
import com.example.homework8cats.domain.repository.CatsRepository
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    fun bindProfileRepository(impl: CatsRepositoryImpl): CatsRepository
}