package com.example.homework6thread

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        GlobalDI.init()
    }
}