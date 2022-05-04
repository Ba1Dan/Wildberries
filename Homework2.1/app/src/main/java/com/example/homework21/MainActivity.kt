package com.example.homework21

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.button.MaterialButton

/**
 * Жизненный цикл Activity:
 * onCreate() - создание активити, здесь выполняется инициализация полей для работы в активити
 * onStart() - активити видна на экране, но пользователь не может еще взиамодеиствовать с ней.
 * onResume() - активити видна на экране и пользователь может взаимодействовать с ней.
 * onPause() -  активити находится не в фокусе пользователя, но может быть видна на экране, например перекрыта окном.
 * onStop() - активити больше не видна на экране, в этом колбэке нужно освободить не нужные ресурсы.
 * onDestroy() - активити уничтожается, на этом работа заканчавается.(Не всегда вызывается)
 *
 * Вызов колбеков, при нажатии на кнопку "Open activity"
 * #MainActivity: onPause
 * #SecondActivity: onCreate
 * #SecondActivity: onStart
 * #SecondActivity: onResume
 * #MainActivity: onStop
 * */
class MainActivity : AppCompatActivity() {

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate")

        val btnOpen: MaterialButton = findViewById(R.id.btn_open)

        btnOpen.setOnClickListener {
            startActivity(SecondActivity.createIntent(this))
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}