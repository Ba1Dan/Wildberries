package com.example.homework6thread.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.homework6thread.domain.StateBackground
import com.example.homework6thread.domain.StopWatchManager

class MainViewModel(private val stopWatchManager: StopWatchManager) : ViewModel() {

    val number: LiveData<String> = stopWatchManager.number
    val color: LiveData<StateBackground> = stopWatchManager.color
    val time: LiveData<String> = stopWatchManager.time

    fun start() {
        stopWatchManager.start()
    }

    fun reset() {
        stopWatchManager.reset()
    }

    fun pause() {
        stopWatchManager.pause()
    }
}