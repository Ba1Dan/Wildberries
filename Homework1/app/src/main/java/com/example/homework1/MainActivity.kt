package com.example.homework1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homework1.broadcast.BroadcastReceiverExampleActivity
import com.example.homework1.content.ContentProviderExampleActivity
import com.example.homework1.service.ServiceExampleActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnService: MaterialButton = findViewById(R.id.btn_service)
        val btnBroadcastReceiver: MaterialButton = findViewById(R.id.btn_broadcast_receive)
        val btnContentProvider: MaterialButton = findViewById(R.id.btn_content_provider)

        btnService.setOnClickListener {
            startActivity(ServiceExampleActivity.createIntent(this))
        }

        btnBroadcastReceiver.setOnClickListener {
            startActivity(BroadcastReceiverExampleActivity.createIntent(this))
        }

        btnContentProvider.setOnClickListener {
            startActivity(ContentProviderExampleActivity.createIntent(this))
        }
    }
}