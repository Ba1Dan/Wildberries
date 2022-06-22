package com.example.homework8cats.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FavouriteCat(
    @SerialName("id")
    val id: Long,
    @SerialName("image")
    val image: Cat
)