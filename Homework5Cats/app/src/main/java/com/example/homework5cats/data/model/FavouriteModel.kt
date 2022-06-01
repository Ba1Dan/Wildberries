package com.example.homework5cats.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class FavouriteModel(
    @SerialName("image_id")
    val id: String
)