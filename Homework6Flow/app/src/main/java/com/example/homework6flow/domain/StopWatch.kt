package com.example.homework6flow.domain

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

class StopWatch {

    val formattedTime: MutableStateFlow<String> = MutableStateFlow(DEFAULT_TIME)
    val changeColor: MutableStateFlow<StateBackground> = MutableStateFlow(StateBackground.Resumed)
    val number: MutableStateFlow<String> = MutableStateFlow(DEFAULT_NUM)

    private var coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    private var isActive = false

    private var timeMillis = 0L
    private var lastTimeStamp = 0L
    private var num = DEFAULT_NUM

    fun start() {
        if (isActive) return

        coroutineScope.launch {
            lastTimeStamp = System.currentTimeMillis()
            this@StopWatch.isActive = true
            while (this@StopWatch.isActive) {
                delay(1000L)
                timeMillis += System.currentTimeMillis() - lastTimeStamp
                lastTimeStamp = System.currentTimeMillis()
                formattedTime.emit(formatTime(timeMillis))
                changeColor.emit(changeColor(timeMillis))
                num += Random.nextInt(0, 10)
                number.emit(num)
            }
        }
    }

    fun pause() {
        isActive = false
    }

    fun reset() {
        coroutineScope.cancel()
        coroutineScope = CoroutineScope(Dispatchers.IO)
        formattedTime.value = (DEFAULT_TIME)
        number.value = (DEFAULT_NUM)
        changeColor.value = StateBackground.Reset
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