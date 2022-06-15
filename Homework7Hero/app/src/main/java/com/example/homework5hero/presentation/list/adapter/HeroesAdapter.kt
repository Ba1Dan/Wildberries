package com.example.homework5hero.presentation.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework5hero.data.model.Hero
import com.example.homework5hero.databinding.ItemHeroBinding
import com.example.homework5hero.presentation.CircularTransformation
import com.squareup.picasso.Picasso

class HeroesAdapter(private val onHeroClickListener: OnHeroClickListener) : RecyclerView.Adapter<HeroesAdapter.HeroViewHolder>() {

    private val data = mutableListOf<Hero>()
    fun setDataSource(newData: List<Hero>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

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
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
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
//                .placeholder(R.drawable.user_placeholder)
//                .error(R.drawable.user_placeholder_error)
                .into(binding.ivPhoto)
        }
    }
}

interface OnHeroClickListener{

    fun onClick(id: Int)
}