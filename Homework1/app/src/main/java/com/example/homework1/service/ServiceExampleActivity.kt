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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_example)

        val btnStart: MaterialButton = findViewById(R.id.btn_start)
        val btnStop: MaterialButton = findViewById(R.id.btn_stop)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = Intent(this, MusicService::class.java)

        btnStart.setOnClickListener {
            startService(intent)
        }

        btnStop.setOnClickListener {
            stopService(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    companion object {

        fun createIntent(context: Context): Intent {
            return Intent(context, ServiceExampleActivity::class.java)
        }
    }
}