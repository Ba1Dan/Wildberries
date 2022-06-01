package com.example.homeworkdota.presentation.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.homeworkdota.data.model.Hero
import com.example.homeworkdota.data.network.RemoteDataSource.Companion.BASE_URL
import com.example.homeworkdota.databinding.ItemHeroBinding

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

    override fun getItemCount(): Int = heroes.size

    class HeroViewHolder(
        private val binding: ItemHeroBinding,
        private val onHeroClickListener: OnHeroClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(hero: Hero) {
            binding.tvName.text = hero.name
            binding.icon.load(BASE_URL + hero.icon)
            binding.root.setOnClickListener {
                onHeroClickListener.onClick(hero)
            }
        }
    }
}

interface OnHeroClickListener {
    fun onClick(hero: Hero)
}