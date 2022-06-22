package com.example.homework5dota.presentation.ui.list

import androidx.recyclerview.widget.DiffUtil
import com.example.homework5dota.data.model.Hero

object HeroesDiffUtil : DiffUtil.ItemCallback<Hero>() {

    override fun areItemsTheSame(oldItem: Hero, newItem: Hero): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Hero, newItem: Hero): Boolean {
        return oldItem == newItem
    }
}