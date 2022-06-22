package com.example.homework8hero.di

import com.example.homework8hero.data.network.HeroesApi
import com.example.homework8hero.data.network.RequestManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideApi(): HeroesApi = RequestManager.service
}