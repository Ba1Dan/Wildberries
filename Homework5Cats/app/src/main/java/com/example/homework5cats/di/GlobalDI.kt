package com.example.homework5cats.di

import com.example.homework5cats.data.RemoteDatsSource
import com.example.homework5cats.data.network.ApiService
import com.example.homework5cats.data.repository.CatsRepositoryImpl
import com.example.homework5cats.domain.usecase.GetCatUseCase
import com.example.homework5cats.domain.usecase.GetFavouriteCatsUseCase
import com.example.homework5cats.domain.usecase.SaveImageInFavouritesUseCase
import com.example.homework5cats.presentation.ui.main.MainViewModel
import com.example.homework5cats.presentation.ui.favourite.FavouriteViewModel

class GlobalDI private constructor() {

    private val remoteDatsSource by lazy { RemoteDatsSource(ApiService.create()) }
    private val repository by lazy { CatsRepositoryImpl(remoteDatsSource) }

    private val getCatUseCase by lazy { GetCatUseCase(repository) }
    private val saveImageInFavouritesUseCase by lazy { SaveImageInFavouritesUseCase(repository) }
    private val getFavouriteCatsUseCase by lazy { GetFavouriteCatsUseCase(repository) }

    val mainViewModel by lazy {
        MainViewModel(
            getCatUseCase = getCatUseCase,
            saveImageInFavouritesUseCase = saveImageInFavouritesUseCase
        )
    }

    val favouriteViewModel by lazy {
        FavouriteViewModel(
            getFavouriteCatsUseCase = getFavouriteCatsUseCase
        )
    }

    companion object {

        lateinit var INSTANCE: GlobalDI

        fun init() {
            INSTANCE = GlobalDI()
        }
    }
}