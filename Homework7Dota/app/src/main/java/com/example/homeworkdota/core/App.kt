package com.example.homeworkdota.core

import android.app.Application
import com.example.homeworkdota.di.GlobalDI

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        GlobalDI.init(applicationContext)
    }
}