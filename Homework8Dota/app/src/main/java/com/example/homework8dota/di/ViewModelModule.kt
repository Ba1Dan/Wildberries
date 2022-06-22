package com.example.homework8dota.di

import androidx.lifecycle.ViewModel
import com.example.homework8dota.presentation.ui.list.HeroesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HeroesViewModel::class)
    fun bindHeroesViewModel(viewModel: HeroesViewModel): ViewModel
}