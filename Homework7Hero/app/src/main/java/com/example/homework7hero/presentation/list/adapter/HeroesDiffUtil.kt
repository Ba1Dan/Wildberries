package com.example.homework7hero.presentation.list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.homework7hero.data.model.Hero

object HeroesDiffUtil : DiffUtil.ItemCallback<Hero>() {

    override fun areItemsTheSame(oldItem: Hero, newItem: Hero): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Hero, newItem: Hero): Boolean {
        return oldItem == newItem
    }
}