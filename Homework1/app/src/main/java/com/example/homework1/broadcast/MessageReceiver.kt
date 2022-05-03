package com.example.homework1.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.widget.Toast

class MessageReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val wifiStateExtra = intent.getIntExtra(
            WifiManager.EXTRA_WIFI_STATE,
            WifiManager.WIFI_STATE_UNKNOWN
        )

        when (wifiStateExtra) {
            WifiManager.WIFI_STATE_ENABLED -> {
                showToast(context, WIFI_ON)
            }
            WifiManager.WIFI_STATE_DISABLED -> {
                showToast(context, WIFI_OFF)
            }
        }
    }

    private fun showToast(context: Context, value: String) {
        Toast.makeText(
            context,
            value,
            Toast.LENGTH_SHORT
        ).show()
    }

    companion object {
        private const val WIFI_ON = "WiFi is ON"
        private const val WIFI_OFF = "WiFi is OFF"
    }
}