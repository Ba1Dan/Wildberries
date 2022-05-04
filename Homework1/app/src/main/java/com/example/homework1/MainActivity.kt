package com.example.homework1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homework1.broadcast.BroadcastReceiverExampleActivity
import com.example.homework1.content.ContentProviderExampleActivity
import com.example.homework1.service.ServiceExampleActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private lateinit var btnService: MaterialButton
    private lateinit var btnBroadcastReceiver: MaterialButton
    private lateinit var btnContentProvider: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        setClickListener()
    }

    private fun initView() {
        btnService = findViewById(R.id.btn_service)
        btnBroadcastReceiver = findViewById(R.id.btn_broadcast_receive)
        btnContentProvider = findViewById(R.id.btn_content_provider)
    }

    private fun setClickListener() {
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