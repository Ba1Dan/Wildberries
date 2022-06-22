package com.example.homework7hero.core

import android.app.Application
import com.example.homework7hero.di.DaggerAppComponent

class App : Application() {

    val component by lazy {
        DaggerAppComponent.factory()
            .create(this)
    }
}