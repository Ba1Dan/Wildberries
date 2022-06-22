package com.example.homework8cats.presentation.ui.favourite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.example.homework8cats.data.model.FavouriteCat
import com.example.homework8cats.databinding.ItemCatBinding

class CatsAdapter : RecyclerView.Adapter<CatsAdapter.CatViewHolder>() {

    private val differ = AsyncListDiffer(this, CatsDiffUtil)

    var cats: List<FavouriteCat>
        set(value) = differ.submitList(value)
        get() = differ.currentList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        return CatViewHolder(ItemCatBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.bind(cats[position])
    }

    override fun getItemCount(): Int = cats.size


    class CatViewHolder(private val binding: ItemCatBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(favouriteCat: FavouriteCat) {
            binding.ivPicture.setImageURI(favouriteCat.image.url)
        }
    }
}