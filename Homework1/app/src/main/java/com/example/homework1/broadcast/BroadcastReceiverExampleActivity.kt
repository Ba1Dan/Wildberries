package com.example.homework1.broadcast

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.homework1.R
import com.google.android.material.button.MaterialButton

/**
 * BroadcastReceiver слушает события системы(включен или выключен WiFi - WIFI_STATE_CHANGED_ACTION).
 * Если включен, то выводится в тосте "Wifi is ON", если выключен - "Wifi is OFF".
 * Пример такого фунционала - Play Market, если нужно скачивать файл большого размера и не включен WiFi,
 * то спрашивает как скачивать файл - WiFi или любая сеть.
 * */
class BroadcastReceiverExampleActivity : AppCompatActivity() {

    private lateinit var messageReceiver: MessageReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast_receiver_example)
        messageReceiver = MessageReceiver()
        registerReceiver(messageReceiver, IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val btnBack: MaterialButton = findViewById(R.id.btn_back)
        btnBack.setOnClickListener {
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(messageReceiver)
    }

    companion object {

        fun createIntent(context: Context): Intent {
            return Intent(context, BroadcastReceiverExampleActivity::class.java)
        }
    }
}