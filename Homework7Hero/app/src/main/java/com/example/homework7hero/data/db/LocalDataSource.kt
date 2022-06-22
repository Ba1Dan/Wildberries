package com.example.homework7hero.data.db

class LocalDataSource(private val sharedPreferencesManager: SharedPreferencesManager) {

    fun getHeroes(): String {
        return sharedPreferencesManager.read()
    }

    fun saveHeroes(heroes: String) {
        sharedPreferencesManager.save(heroes)
    }
}