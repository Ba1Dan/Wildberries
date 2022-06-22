package com.example.homework8hero.di

import androidx.lifecycle.ViewModel
import com.example.homework8hero.presentation.ui.detail.DetailHeroViewModel
import com.example.homework8hero.presentation.ui.list.ListHeroesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailHeroViewModel::class)
    fun bindChatViewModel(viewModel: DetailHeroViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ListHeroesViewModel::class)
    fun bindStreamsViewModel(viewModel: ListHeroesViewModel): ViewModel
}