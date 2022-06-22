package com.example.homework5hero.core

import android.app.Application
import com.example.homework5hero.di.DaggerAppComponent

class App : Application() {

    val component by lazy {
        DaggerAppComponent.factory()
            .create(this)
    }
}