package com.example.homework6coroutines.core

import android.app.Application
import com.example.homework6coroutines.di.GlobalDI

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        GlobalDI.init()
    }
}