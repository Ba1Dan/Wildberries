package com.example.homework4.repository

import com.example.homework4.model.Chat
import com.example.homework4.model.Message
import com.example.homework4.model.MessageType
import kotlin.random.Random

class Repository {

    private val chats = mutableListOf<Chat>()

    init {
        generateChats()
    }

    fun getChats(countOfPaging: Int): List<Chat> {
        return ArrayList(chats).take(SIZE_PAGING * countOfPaging)
    }

    fun addChat(chat: Chat) {
        chats.add(chat)
    }

    fun deleteChat(position: Int) {
        chats.removeAt(position)
    }

    fun getMessages(chatId: Int, countOfPaging: Int): List<Message> {
        return chats.filter { it.id == chatId }[0].messages.takeLast(SIZE_PAGING * countOfPaging)
    }

    fun addMessage(chatId: Int, message: Message) {
        chats.filter { it.id == chatId }[0].messages.add(message)
    }

    fun getIndexMessage(chatId: Int): Int {
        return chats.filter { it.id == chatId }[0].messages.size
    }

    fun editMessage(chatId: Int, messageId: Int, newMessage: String) {
        chats.filter { it.id == chatId }[0].messages[messageId].body = newMessage
    }

    fun getIndexChat(): Int {
        return chats.size
    }


    fun deleteMessage(chatId: Int, messageId: Int) {
        chats.filter { it.id == chatId }[0].messages.removeAt(messageId)
    }

    private fun generateChats() {
        val n = Random.nextInt(41, 100)
        for (i in 0 until n) {
            chats.add(
                Chat(
                    i,
                    (1..10).map { allowedChars.random() }.joinToString(""),
                    "",
                    Random.nextInt(21, 100),
                    generateMessages()
                )
            )
        }
    }

    private fun generateMessages(): MutableList<Message> {
        val n = Random.nextInt(41, 100)
        val result = mutableListOf<Message>()
        for (i in 0 until n) {
            result.add(
                Message(
                    i,
                    (1..25).map { allowedChars.random() }.joinToString(""),
                    authors.random(),
                    MessageType.values().random()
                )
            )
        }
        return result
    }


    companion object {
        private val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        private val authors = listOf("Данияр", "Иван", "Игорь", "Инокентий")
        private const val SIZE_PAGING = 10
    }
}