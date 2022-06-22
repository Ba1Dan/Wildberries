package com.example.homework5cats.di

import com.example.homework5cats.data.repository.CatsRepositoryImpl
import com.example.homework5cats.domain.repository.CatsRepository
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    fun bindProfileRepository(impl: CatsRepositoryImpl): CatsRepository
}