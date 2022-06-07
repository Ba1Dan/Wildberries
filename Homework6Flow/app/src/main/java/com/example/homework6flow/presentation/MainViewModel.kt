package com.example.homework6flow.presentation

import androidx.lifecycle.ViewModel
import com.example.homework6flow.domain.StateBackground
import com.example.homework6flow.domain.StopWatch
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel(private val stopWatch: StopWatch) : ViewModel() {

    val formattedTime: MutableStateFlow<String> = stopWatch.formattedTime
    val changeColor: MutableStateFlow<StateBackground> = stopWatch.changeColor
    val number: MutableStateFlow<String> = stopWatch.number

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