package com.example.homework7cats.core

import android.app.Application
import com.example.homework7cats.di.DaggerAppComponent
import com.facebook.drawee.backends.pipeline.Fresco

class App : Application() {

    val component by lazy {
        DaggerAppComponent.factory()
            .create(this)
    }

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}