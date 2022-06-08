package com.example.homework6thread.di

import com.example.homework6thread.presentation.MainViewModel
import com.example.homework6thread.domain.StopWatchManager

class GlobalDI private constructor() {

    private val stopWatchManager by lazy { StopWatchManager() }
    val mainViewModel by lazy {
        MainViewModel(stopWatchManager)
    }

    companion object {

        lateinit var INSTANCE: GlobalDI

        fun init() {
            INSTANCE = GlobalDI()
        }
    }
}