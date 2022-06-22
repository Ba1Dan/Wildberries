package com.example.homework5dota.core

import android.app.Application
import com.example.homework5dota.di.DaggerAppComponent

class App : Application() {

    val component by lazy {
        DaggerAppComponent.factory()
            .create(this)
    }
}