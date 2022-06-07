package com.example.homework6coroutines.domain

sealed class StateBackground {

    object Resumed : StateBackground()

    class Changed(val color: Int) : StateBackground()

    object Reset : StateBackground()
}