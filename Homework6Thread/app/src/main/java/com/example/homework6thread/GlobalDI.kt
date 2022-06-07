package com.example.homework6thread

class GlobalDI private constructor() {

    val mainViewModel by lazy {
        MainViewModel()
    }

    companion object {

        lateinit var INSTANCE: GlobalDI

        fun init() {
            INSTANCE = GlobalDI()
        }
    }
}