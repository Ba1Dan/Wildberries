package com.example.homework5hero.data.model

import com.google.gson.annotations.SerializedName

class Hero(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("biography")
    val biography: Biography,
    @SerializedName("images")
    val image: Image
)