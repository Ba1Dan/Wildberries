package com.example.homework8dota.data.db


class LocalDataSource(private val fileManager: FileManager) {

    fun writeToFile(data: String) = fileManager.writeToFile(data)

    fun readFromFile(): String = fileManager.readFromFile()

    companion object {
        private const val FILE_NAME = "data.txt"
    }
}