package com.example.homework4.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.homework4.model.Message

class MessageDiffUtil : DiffUtil.ItemCallback<Message>() {

    override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem == newItem
    }
}