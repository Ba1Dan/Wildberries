package com.example.homework6coroutines.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.homework6coroutines.domain.StateBackground
import com.example.homework6coroutines.domain.StopWatch

class MainViewModel(private val stopWatch: StopWatch) : ViewModel() {

    val formattedTime: LiveData<String> = stopWatch.formattedTime
    val changeColor: LiveData<StateBackground> = stopWatch.changeColor
    val number: LiveData<String> = stopWatch.number

    fun start() {
        stopWatch.start()
    }

    fun pause() {
        stopWatch.pause()
    }

    fun reset() {
        stopWatch.reset()
    }
}