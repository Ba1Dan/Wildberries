package com.example.homework8hero.data.db

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferencesManager @Inject constructor(private val context: Context) {

    private val mSettings by lazy { context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)}

    fun save(data: String) {
        val editor: SharedPreferences.Editor = mSettings.edit()
        editor.clear()
        editor.putString(APP_PREFERENCES_NAME, data)
        editor.apply()
    }

    fun read(): String = mSettings.getString(APP_PREFERENCES_NAME, "") ?: ""

    companion object {
        private const val APP_PREFERENCES = "data"
        const val APP_PREFERENCES_NAME = "json"
    }
}