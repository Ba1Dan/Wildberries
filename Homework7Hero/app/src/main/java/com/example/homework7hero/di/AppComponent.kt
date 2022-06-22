package com.example.homework7hero.di

import android.content.Context
import com.example.homework7hero.presentation.MainActivity
import com.example.homework7hero.presentation.detail.DetailHeroFragment
import com.example.homework7hero.presentation.list.ListHeroesFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DomainModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(listHeroesFragment: ListHeroesFragment)
    fun inject(detailHeroFragment: DetailHeroFragment)

    @Component.Factory
    interface ApplicationComponentFactory {

        fun create(
            @BindsInstance context: Context,
        ): AppComponent
    }
}