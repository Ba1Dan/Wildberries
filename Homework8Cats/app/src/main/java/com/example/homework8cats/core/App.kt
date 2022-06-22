package com.example.homework8cats.core

import android.app.Application
import com.example.homework8cats.di.GlobalDI
import com.facebook.drawee.backends.pipeline.Fresco

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
        GlobalDI.init(applicationContext)
    }
}