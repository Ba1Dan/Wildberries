package com.example.homework4.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.example.homework4.databinding.ItemIncomingMessageBinding
import com.example.homework4.databinding.ItemOutgoingMessageBinding
import com.example.homework4.model.Message
import com.example.homework4.model.MessageType

class MessageAdapter(private val onClickListenerMessage: OnClickListenerMessage) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val differ = AsyncListDiffer(this, MessageDiffUtil())

    var messages: List<Message>
        set(value) = differ.submitList(value)
        get() = differ.currentList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MessageType.IN.ordinal -> {
                InComingMessageViewHolder(
                    ItemIncomingMessageBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                    onClickListenerMessage
                )
            }

            MessageType.OUT.ordinal -> {
                OutGoingMessageViewHolder(
                    ItemOutgoingMessageBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                    onClickListenerMessage
                )
            }
            else -> {
                throw Exception("Unknown ViewType ${parent.resources.getResourceName(viewType)}")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            MessageType.IN.ordinal -> {
                (holder as InComingMessageViewHolder).bind(messages[position])
            }
            MessageType.OUT.ordinal -> {
                (holder as OutGoingMessageViewHolder).bind(messages[position])
            }
        }


    }

    override fun getItemCount(): Int = messages.size

    class OutGoingMessageViewHolder(
        private val binding: ItemOutgoingMessageBinding,
        private val onClickListenerMessage: OnClickListenerMessage
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(message: Message) {
            binding.tvMessage.text = message.body

            binding.root.setOnLongClickListener {
                onClickListenerMessage.onClick(message.id, message.body)
                true
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return messages[position].type.ordinal
    }

    class InComingMessageViewHolder(
        private val binding: ItemIncomingMessageBinding,
        private val onClickListenerMessage: OnClickListenerMessage,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(message: Message) {
            binding.tvMessage.text = message.body
            binding.tvAuthor.text = message.author

            binding.root.setOnLongClickListener {
                onClickListenerMessage.onClick(message.id, message.body)
                true
            }
        }
    }
}

interface OnClickListenerMessage {
    fun onClick(messageId: Int, message: String)
}