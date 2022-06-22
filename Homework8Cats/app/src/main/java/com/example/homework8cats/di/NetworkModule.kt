package com.example.homework8cats.di

import com.example.homework8cats.data.network.ApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideApiService(): ApiService = ApiService.create()
}