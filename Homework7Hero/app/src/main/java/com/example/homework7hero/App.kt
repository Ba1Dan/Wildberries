package com.example.homework7hero

import android.app.Application
import com.example.homework7hero.di.GlobalDI

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        GlobalDI.init(applicationContext)
    }
}