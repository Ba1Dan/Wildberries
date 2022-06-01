package com.example.homework5cats.core

import android.app.Application
import com.example.homework5cats.di.GlobalDI
import com.facebook.drawee.backends.pipeline.Fresco

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
        GlobalDI.init()
    }
}