package com.example.homework5cats.di

import android.content.Context
import androidx.room.Room
import com.example.homework5cats.data.LocalDataSource
import com.example.homework5cats.data.RemoteDatsSource
import com.example.homework5cats.data.db.CatsDatabase
import com.example.homework5cats.data.network.ApiService
import com.example.homework5cats.data.repository.CatsRepositoryImpl
import com.example.homework5cats.domain.usecase.GetCatUseCase
import com.example.homework5cats.domain.usecase.SaveImageInFavouritesUseCase
import com.example.homework5cats.presentation.ui.main.MainViewModel
import com.example.homework5cats.presentation.ui.favourite.FavouriteViewModel

class GlobalDI private constructor(private val context: Context) {

    private val catsDatabase = CatsDatabase.create(context)
    private val remoteDatsSource by lazy { RemoteDatsSource(ApiService.create()) }
    private val localDatsSource by lazy {
        LocalDataSource(
            catsDatabase.favouriteCatsDao()
        )
    }
    private val repository by lazy { CatsRepositoryImpl(remoteDatsSource, localDatsSource) }

    private val getCatUseCase by lazy { GetCatUseCase(repository) }
    private val saveImageInFavouritesUseCase by lazy { SaveImageInFavouritesUseCase(repository) }

    val mainViewModel by lazy {
        MainViewModel(
            getCatUseCase = getCatUseCase,
            saveImageInFavouritesUseCase = saveImageInFavouritesUseCase
        )
    }

    val favouriteViewModel by lazy { FavouriteViewModel(repository) }

    companion object {

        lateinit var INSTANCE: GlobalDI

        fun init(context: Context) {
            INSTANCE = GlobalDI(context)
        }
    }
}