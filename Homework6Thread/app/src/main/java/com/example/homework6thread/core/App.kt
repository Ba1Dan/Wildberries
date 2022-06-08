package com.example.homework6thread.core

import android.app.Application
import com.example.homework6thread.di.GlobalDI

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        GlobalDI.init()
    }
}