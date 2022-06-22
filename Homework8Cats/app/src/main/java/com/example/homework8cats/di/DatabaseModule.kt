package com.example.homework8cats.di

import android.content.Context
import com.example.homework8cats.data.db.CatsDatabase
import com.example.homework8cats.data.db.FavouriteCatsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): CatsDatabase = CatsDatabase.create(context)

    @Singleton
    @Provides
    fun provide(catsDatabase: CatsDatabase): FavouriteCatsDao = catsDatabase.favouriteCatsDao()
}