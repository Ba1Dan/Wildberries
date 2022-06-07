package com.example.homework6coroutines.di

import com.example.homework6coroutines.presentation.MainViewModel
import com.example.homework6coroutines.domain.StopWatch

class GlobalDI {

    val mainViewModel by lazy {
        MainViewModel(StopWatch())
    }

    companion object {

        lateinit var INSTANCE: GlobalDI

        fun init() {
            INSTANCE = GlobalDI()
        }
    }
}