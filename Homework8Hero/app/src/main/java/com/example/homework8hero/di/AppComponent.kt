package com.example.homework8hero.di

import android.content.Context
import com.example.homework8hero.presentation.MainActivity
import com.example.homework8hero.presentation.ui.detail.DetailHeroFragment
import com.example.homework8hero.presentation.ui.list.ListHeroesFragment
import com.example.homework8hero.presentation.ui.info.InfoFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DomainModule::class, RouterModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(listHeroesFragment: ListHeroesFragment)
    fun inject(detailHeroFragment: DetailHeroFragment)
    fun inject(infoFragment: InfoFragment)

    @Component.Factory
    interface ApplicationComponentFactory {

        fun create(
            @BindsInstance context: Context,
        ): AppComponent
    }
}