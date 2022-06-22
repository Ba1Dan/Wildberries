package com.example.homework7dota.core

import android.app.Application
import com.example.homework7dota.di.DaggerAppComponent

class App : Application() {

    val component by lazy {
        DaggerAppComponent.factory()
            .create(this)
    }

}