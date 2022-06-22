package com.example.homework8dota.data.db

import android.content.Context
import android.util.Log
import java.io.*

class FileManager(private val context: Context) {

    fun writeToFile(data: String) {
        var outputStreamWriter: OutputStreamWriter? = null
        try {
            outputStreamWriter = OutputStreamWriter(context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE))
            outputStreamWriter.write(data)

        } catch (e: IOException) {
            Log.e("Exception", "File write failed: $e")
        } finally {
            outputStreamWriter?.close()
        }
    }

    fun readFromFile(): String {
        var ret = ""
        try {
            val inputStream: InputStream? = context.openFileInput(FILE_NAME)
            if (inputStream != null) {
                val inputStreamReader = InputStreamReader(inputStream)
                val bufferedReader = BufferedReader(inputStreamReader)
                var receiveString: String? = ""
                val stringBuilder = StringBuilder()
                while (bufferedReader.readLine().also { receiveString = it } != null) {
                    stringBuilder.append("\n").append(receiveString)
                }
                inputStream.close()
                ret = stringBuilder.toString()
            }
        } catch (e: FileNotFoundException) {
            Log.e("login activity", "File not found: " + e.toString())
        } catch (e: IOException) {
            Log.e("login activity", "Can not read file: $e")
        }
        return ret
    }

    companion object {
        private const val FILE_NAME = "data.txt"
    }
}