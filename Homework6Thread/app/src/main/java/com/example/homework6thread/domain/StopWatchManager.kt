package com.example.homework6thread.domain

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import java.util.*
import kotlin.random.Random

class StopWatchManager {

    val time: MutableLiveData<String> = MutableLiveData(DEFAULT_TIME)
    val number: MutableLiveData<String> = MutableLiveData("")
    val color: MutableLiveData<StateBackground> = MutableLiveData()

    private var seconds = 0
    private var result = EMPTY_STRING
    private var isActive = true

    private lateinit var handler: Handler

    private val thread = Thread {
        Looper.prepare()
        handler = Handler(Looper.myLooper()!!)
        handler.postDelayed(timeUpdaterRunnable, 1000)
        Looper.loop()
    }

    fun start() {
        thread.start()
    }

    fun pause() {
        isActive = false
    }

    fun reset() {
        thread.interrupt()
        isActive = false
        seconds = 0
        result = EMPTY_STRING
        color.value = StateBackground.Reset
        number.value = EMPTY_STRING
    }

    private val timeUpdaterRunnable: Runnable = object : Runnable {
        override fun run() {
            val time = getTime()

            if (seconds != 0 && isActive && seconds % 20 == 0) {
                updateColor()
            }

            if (isActive) {
                updateTimeAndNum(time)
            }

            handler.postDelayed(this, 1000)
        }
    }

    private fun getTime(): String {
        val hours = seconds / 3600
        val minutes = seconds % 3600 / 60
        val secs = seconds % 60

        return String.format(
            Locale.getDefault(),
            FORMAT_TIME, hours,
            minutes, secs
        )
    }

    private fun updateColor() {
        val randomColor: Int = -Random.nextInt(255 * 255 * 255)
        color.postValue(StateBackground.Changed(randomColor))
    }

    private fun updateTimeAndNum(time: String) {
        seconds++
        result += Random.nextInt(0, 10)
        this@StopWatchManager.time.postValue(time)
        number.postValue(result)
    }

    companion object {
        private const val DEFAULT_TIME = "00:00:00"
        private const val FORMAT_TIME = "%d:%02d:%02d"
        private const val EMPTY_STRING = ""
    }
}