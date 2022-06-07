package com.example.homework6coroutines.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

class StopWatch {

    private val _formattedTime: MutableLiveData<String> = MutableLiveData(DEFAULT_TIME)
    private val _changeColor: MutableLiveData<StateBackground> = MutableLiveData(StateBackground.Resumed)
    private val _number: MutableLiveData<String> = MutableLiveData(DEFAULT_NUM)

    private var coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    private var isActive = false

    private var timeMillis = 0L
    private var lastTimeStamp = 0L
    private var num = DEFAULT_NUM


    val formattedTime: LiveData<String> = _formattedTime
    val changeColor: LiveData<StateBackground> = _changeColor
    val number: LiveData<String> = _number

    fun start() {
        if (isActive) return

        coroutineScope.launch {
            lastTimeStamp = System.currentTimeMillis()
            this@StopWatch.isActive = true
            while (this@StopWatch.isActive) {
                delay(1000L)
                timeMillis += System.currentTimeMillis() - lastTimeStamp
                lastTimeStamp = System.currentTimeMillis()
                _formattedTime.postValue(formatTime(timeMillis))
                _changeColor.postValue(changeColor(timeMillis))
                num += Random.nextInt(0, 10)
                _number.postValue(num)
            }
        }
    }

    fun pause() {
        isActive = false
    }

    fun reset() {
        coroutineScope.cancel()
        coroutineScope = CoroutineScope(Dispatchers.IO)
        _formattedTime.value = (DEFAULT_TIME)
        _number.value = (DEFAULT_NUM)
        _changeColor.value = StateBackground.Reset
        timeMillis = 0L
        lastTimeStamp = 0L
        isActive = false
    }

    private fun formatTime(time: Long): String {
        val simpleDateFormat = SimpleDateFormat("HH:mm:ss", Locale("ru"))
        val date = Date(time)
        return simpleDateFormat.format(date)
    }

    private fun changeColor(currentTime: Long): StateBackground {
        val simpleDateFormat = SimpleDateFormat("HH:mm:ss", Locale("ru"))
        val date = Date(currentTime)
        val time = simpleDateFormat.format(date).split(":")
        val seconds = time[2].toInt()
        if (time[0].toInt() == 0 && time[1].toInt() == 0 && seconds == 0) {
            return StateBackground.Resumed
        }
        if (seconds % 20 == 0) {
            return StateBackground.Changed(-Random.nextInt(255 * 255 * 255))
        }
        return StateBackground.Resumed
    }

    companion object {
        private const val DEFAULT_NUM = ""
        private const val DEFAULT_TIME = "00:00:00"
    }
}