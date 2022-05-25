package com.example.homework4.model

import java.util.*

data class Message(
    val id: Int,
    var body: String,
    val author: String,
    val type: MessageType
)

enum class MessageType {
    IN, OUT
}