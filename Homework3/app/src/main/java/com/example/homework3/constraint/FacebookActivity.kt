package com.example.homework3.constraint

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homework3.R

class FacebookActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facebook)
        supportActionBar?.hide()
    }


    companion object {

        fun createIntent(context: Context): Intent {
            return Intent(context, FacebookActivity::class.java)
        }
    }
}