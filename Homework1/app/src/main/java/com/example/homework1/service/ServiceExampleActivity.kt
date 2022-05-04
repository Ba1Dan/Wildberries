package com.example.homework1.service

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homework1.R
import com.google.android.material.button.MaterialButton

/**
 * Создается сервис MusicService, по нажатию кнопки "Start" стартует этот сервис
 * и начинается воспроизведение рингтона. При сворачивании приложения, рингтон продолжает играть.
 * При нажатии кнопки "Stop", сервис заканчивает свою работу и рингтон перестает звучать.
 * Service используются в музыкальных проигрывателях - Spotify, Яндекс.Музыка.
 * */
class ServiceExampleActivity : AppCompatActivity() {

    private lateinit var btnStart: MaterialButton
    private lateinit var btnStop: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_example)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initView()
        setClickListener()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun initView() {
        btnStart = findViewById(R.id.btn_start)
        btnStop = findViewById(R.id.btn_stop)
    }

    private fun setClickListener() {
        val intentService = Intent(this, MusicService::class.java)

        btnStart.setOnClickListener {
            startService(intentService)
        }

        btnStop.setOnClickListener {
            stopService(intentService)
        }
    }

    companion object {

        fun createIntent(context: Context): Intent {
            return Intent(context, ServiceExampleActivity::class.java)
        }
    }
}