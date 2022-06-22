package com.example.homework7dota.data.db

import javax.inject.Inject


class LocalDataSource @Inject constructor(private val fileManager: FileManager) {

    fun writeToFile(data: String) = fileManager.writeToFile(data)

    fun readFromFile(): String = fileManager.readFromFile()

    companion object {
        private const val FILE_NAME = "data.txt"
    }
}