package com.example.homework8cats.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Cat(
    @SerialName("id")
    val id: String,
    @SerialName("url")
    val url: String
)