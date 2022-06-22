package com.example.homework8dota.core

import android.app.Application
import com.example.homework8dota.di.DaggerAppComponent

class App : Application() {

    val component by lazy {
        DaggerAppComponent.factory()
            .create(this)
    }
}