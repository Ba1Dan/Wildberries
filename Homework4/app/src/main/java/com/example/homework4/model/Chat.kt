package com.example.homework4.model

data class Chat(
    val id: Int,
    val title: String,
    val picture: String,
    val countOfUnread: Int,
    val messages: MutableList<Message>
)