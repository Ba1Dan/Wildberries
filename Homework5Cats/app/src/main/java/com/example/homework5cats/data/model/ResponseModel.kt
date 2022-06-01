package com.example.homework5cats.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ResponseModel(
    @SerialName("id")
    val id: Long,
    @SerialName("message")
    val message: String
)