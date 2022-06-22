package com.example.homework7hero.data.db

import javax.inject.Inject

class LocalDataSource @Inject constructor(private val sharedPreferencesManager: SharedPreferencesManager) {

    fun getHeroes(): String {
        return sharedPreferencesManager.read()
    }

    fun saveHeroes(heroes: String) {
        sharedPreferencesManager.save(heroes)
    }
}