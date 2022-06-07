package com.example.homework6flow.core

import android.app.Application
import com.example.homework6flow.di.GlobalDI

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        GlobalDI.init()
    }
}