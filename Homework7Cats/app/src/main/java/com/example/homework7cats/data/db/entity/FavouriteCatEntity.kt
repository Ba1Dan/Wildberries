package com.example.homework7cats.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.homework7cats.data.db.entity.FavouriteCatEntity.Companion.TABLE_NAME_FAVOURITE_CATS
import com.example.homework7cats.data.model.Cat
import kotlinx.serialization.SerialName

@Entity(tableName = TABLE_NAME_FAVOURITE_CATS)
class FavouriteCatEntity(
    @SerialName("id")
    @PrimaryKey
    val id: Long,
    @SerialName("image")
    val image: Cat
){
    companion object {
        const val TABLE_NAME_FAVOURITE_CATS = "favourite_cats"
    }
}
