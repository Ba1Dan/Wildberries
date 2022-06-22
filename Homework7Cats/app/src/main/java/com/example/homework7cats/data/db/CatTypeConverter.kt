package com.example.homework7cats.data.db

import androidx.room.TypeConverter
import com.example.homework7cats.data.model.Cat
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@ExperimentalSerializationApi
class CatTypeConverter {

    @TypeConverter
    fun reactionsToString(value: Cat): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun stringToReactions(value: String): Cat {
        return Json.decodeFromString(value)
    }
}