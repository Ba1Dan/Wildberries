package com.example.homework8hero.presentation.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.example.homework8hero.data.model.Hero
import com.example.homework8hero.databinding.ItemHeroBinding
import com.example.homework8hero.presentation.CircularTransformation
import com.squareup.picasso.Picasso

class HeroesAdapter(private val onHeroClickListener: OnHeroClickListener) : RecyclerView.Adapter<HeroesAdapter.HeroViewHolder>() {

    private val differ = AsyncListDiffer(this, HeroesDiffUtil)

    var heroes: List<Hero>
        set(value) = differ.submitList(value)
        get() = differ.currentList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        return HeroViewHolder(
            ItemHeroBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onHeroClickListener
        )
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        holder.bind(heroes[position])
    }

    override fun getItemCount(): Int {
        return heroes.size
    }

    class HeroViewHolder(private val binding: ItemHeroBinding, private val onHeroClickListener: OnHeroClickListener) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(hero: Hero) {
            binding.tvNameHero.text = hero.name
            binding.root.setOnClickListener {
                onHeroClickListener.onClick(hero.id)
            }
            Picasso.with(binding.root.context)
                .load(hero.image.url)
                .transform(CircularTransformation(300))
                .into(binding.ivPhoto)
        }
    }
}

interface OnHeroClickListener{

    fun onClick(id: Int)
}