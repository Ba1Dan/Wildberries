package com.example.homework8dota.core

import android.app.Application
import com.example.homework8dota.di.GlobalDI

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        GlobalDI.init(applicationContext)
    }
}