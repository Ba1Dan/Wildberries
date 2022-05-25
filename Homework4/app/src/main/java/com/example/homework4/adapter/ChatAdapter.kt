package com.example.homework4.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.example.homework4.databinding.ItemChatBinding
import com.example.homework4.model.Chat
import com.example.homework4.model.Message

class ChatAdapter(private val onClickListenerChat: OnClickListenerChat) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    private val differ = AsyncListDiffer(this, ChatDiffUtil())

    var chats: List<Chat>
        set(value) = differ.submitList(value)
        get() = differ.currentList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return ChatViewHolder(ItemChatBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(chats[position])

        holder.itemView.setOnClickListener {
            onClickListenerChat.onClick(chats[position].id, chats[position].title)
        }
    }

    override fun getItemCount(): Int = chats.size

    class ChatViewHolder(private val binding: ItemChatBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(chat: Chat) {
            binding.tvTitleChat.text = chat.title
            binding.tvCountUnreadMessages.text = chat.countOfUnread.toString()
        }
    }
}

interface OnClickListenerChat {
    fun onClick(chatId: Int, chatTitle: String)
}