package com.example.homework8cats.di

import android.content.Context
import com.example.homework8cats.presentation.ui.MainActivity
import com.example.homework8cats.presentation.ui.favourite.FavouriteFragment
import com.example.homework8cats.presentation.ui.info.InfoFragment
import com.example.homework8cats.presentation.ui.main.MainFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DomainModule::class, DatabaseModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(mainFragment: MainFragment)
    fun inject(favouriteFragment: FavouriteFragment)
    fun inject(infoFragment: InfoFragment)

    @Component.Factory
    interface ApplicationComponentFactory {

        fun create(
            @BindsInstance context: Context,
        ): AppComponent
    }
}