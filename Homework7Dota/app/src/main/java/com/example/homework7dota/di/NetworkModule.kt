package com.example.homework7dota.di

import com.example.homework7dota.data.model.Hero
import com.example.homework7dota.data.network.RequestManager
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import java.lang.reflect.ParameterizedType
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = RequestManager.createClient()

    @Singleton
    @Provides
    fun provideAdapterMoshi(moshi: Moshi, type: ParameterizedType): JsonAdapter<List<Hero>> =
        moshi.adapter(type)

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = RequestManager.createMoshi()

    @Singleton
    @Provides
    fun provideParameterizedTypeMoshi(): ParameterizedType {
        return Types.newParameterizedType(
            MutableList::class.java,
            Hero::class.java
        )
    }
}