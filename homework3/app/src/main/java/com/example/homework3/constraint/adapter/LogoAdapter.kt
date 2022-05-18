package com.example.homework3.constraint.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.homework3.R

class LogoAdapter : RecyclerView.Adapter<LogoAdapter.LogoViewHolder>() {


    private val data = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogoViewHolder {
       return LogoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_logo, parent, false))
    }

    override fun onBindViewHolder(holder: LogoViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(newData: List<String>) {
        data.addAll(newData)
        notifyDataSetChanged()
    }

    class LogoViewHolder(private val itemView: View): RecyclerView.ViewHolder(itemView) {

        private val ivLogo: ImageView = itemView.findViewById(R.id.logo)

        fun bind(image: String) {
        }
    }
}