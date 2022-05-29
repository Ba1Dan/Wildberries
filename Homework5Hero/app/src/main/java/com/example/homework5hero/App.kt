package com.example.homework5hero

import android.app.Application
import com.example.homework5hero.di.GlobalDI

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        GlobalDI.init()
    }
}