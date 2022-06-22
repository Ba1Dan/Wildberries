package com.example.homework5hero.di

import com.example.homework5hero.data.network.HeroesApi
import com.example.homework5hero.data.network.RequestManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideApi(): HeroesApi = RequestManager.service
}