package com.example.homework8dota.di

import android.content.Context
import com.example.homework8dota.presentation.ui.MainActivity
import com.example.homework8dota.presentation.ui.detail.DetailFragment
import com.example.homework8dota.presentation.ui.info.InfoFragment
import com.example.homework8dota.presentation.ui.list.HeroesFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DomainModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(listHeroesFragment: HeroesFragment)
    fun inject(detailHeroFragment: DetailFragment)
    fun inject(infoFragment: InfoFragment)

    @Component.Factory
    interface ApplicationComponentFactory {

        fun create(
            @BindsInstance context: Context,
        ): AppComponent
    }
}