package com.example.homework8hero.core

import android.app.Application
import com.example.homework8hero.di.DaggerAppComponent

class App : Application() {

    val component by lazy {
        DaggerAppComponent.factory()
            .create(this)
    }
}