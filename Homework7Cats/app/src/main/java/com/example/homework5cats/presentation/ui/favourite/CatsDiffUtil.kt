package com.example.homework5cats.presentation.ui.favourite

import androidx.recyclerview.widget.DiffUtil
import com.example.homework5cats.data.model.FavouriteCat

object CatsDiffUtil : DiffUtil.ItemCallback<FavouriteCat>() {

    override fun areItemsTheSame(oldItem: FavouriteCat, newItem: FavouriteCat): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: FavouriteCat, newItem: FavouriteCat): Boolean {
        return oldItem == newItem
    }
}