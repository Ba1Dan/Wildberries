package com.example.homework6thread.domain

sealed class StateBackground {

    object Resumed : StateBackground()

    class Changed(val color: Int) : StateBackground()

    object Reset : StateBackground()
}