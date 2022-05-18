package com.example.homework3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homework3.constraint.AudioActivity
import com.example.homework3.constraint.CalculatorActivity
import com.example.homework3.constraint.FacebookActivity
import com.example.homework3.constraint.TelegramActivity
import com.example.homework3.without.AudioActivityWithoutConstraint
import com.example.homework3.without.CalculatorActivityWithoutConstraint
import com.example.homework3.without.FacebookActivityWithoutConstraint
import com.example.homework3.without.TelegramActivityWithoutConstraint
import com.google.android.material.button.MaterialButton

/**
 * Время отрисовки
 * AudioActivity +743ms AudioActivityWithoutConstraint 269ms
 * CalculatorActivity 385ms CalculatorActivityWithoutConstraint 254ms
 * FacebookActivity 166ms FacebookActivityWithoutConstraint 250ms
 * TelegramActivity 328ms TelegramActivityWithoutConstraint 213ms
 *
 *
 *
 * */
class MainActivity : AppCompatActivity() {

    private lateinit var btnOpenTelegram: MaterialButton
    private lateinit var btnOpenCalculator: MaterialButton
    private lateinit var btnOpenAudio: MaterialButton
    private lateinit var btnOpenFacebook: MaterialButton
    private lateinit var btnOpenAudioWithoutConstraint: MaterialButton
    private lateinit var btnOpenCalculatorWithoutConstraint: MaterialButton
    private lateinit var btnOpenTelegramWithoutConstraint: MaterialButton
    private lateinit var btnOpenFacebookWithoutConstraint: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        setClickListener()
    }

    private fun initView() {
        btnOpenTelegram = findViewById(R.id.btn_open_telegram)
        btnOpenCalculator = findViewById(R.id.btn_open_calculator)
        btnOpenAudio = findViewById(R.id.btn_open_audio_player)
        btnOpenFacebook = findViewById(R.id.btn_open_facebook)
        btnOpenAudioWithoutConstraint = findViewById(R.id.btn_open_audio_player_without_constraint)
        btnOpenCalculatorWithoutConstraint = findViewById(R.id.btn_open_calculator_without_constraint)
        btnOpenTelegramWithoutConstraint = findViewById(R.id.btn_open_telegram_without_constraint)
        btnOpenFacebookWithoutConstraint = findViewById(R.id.btn_open_facebook_without_constraint)
    }

    private fun setClickListener() {
        btnOpenTelegram.setOnClickListener {
            startActivity(TelegramActivity.createIntent(this))
        }

        btnOpenCalculator.setOnClickListener {
            startActivity(CalculatorActivity.createIntent(this))
        }

        btnOpenAudio.setOnClickListener {
            startActivity(AudioActivity.createIntent(this))
        }

        btnOpenFacebook.setOnClickListener {
            startActivity(FacebookActivity.createIntent(this))
        }

        btnOpenAudioWithoutConstraint.setOnClickListener {
            startActivity(AudioActivityWithoutConstraint.createIntent(this))
        }


        btnOpenCalculatorWithoutConstraint.setOnClickListener {
            startActivity(CalculatorActivityWithoutConstraint.createIntent(this))
        }


        btnOpenTelegramWithoutConstraint.setOnClickListener {
            startActivity(TelegramActivityWithoutConstraint.createIntent(this))
        }


        btnOpenFacebookWithoutConstraint.setOnClickListener {
            startActivity(FacebookActivityWithoutConstraint.createIntent(this))
        }
    }
}