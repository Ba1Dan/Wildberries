package com.example.homework7cats.di

import com.example.homework7cats.data.repository.CatsRepositoryImpl
import com.example.homework7cats.domain.repository.CatsRepository
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    fun bindProfileRepository(impl: CatsRepositoryImpl): CatsRepository
}