package com.example.homework6flow.di

import com.example.homework6flow.domain.StopWatch
import com.example.homework6flow.presentation.MainViewModel

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