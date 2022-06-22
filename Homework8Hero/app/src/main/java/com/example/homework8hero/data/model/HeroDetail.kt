package com.example.homework8hero.data.model

import com.google.gson.annotations.SerializedName

class HeroDetail(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("biography")
    val biography: Biography,
    @SerializedName("powerstats")
    val powerStats: PowerStats,
    @SerializedName("images")
    val image: Image
)