package com.example.homework5hero.data.model

import com.google.gson.annotations.SerializedName

class Biography(
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("aliases")
    val aliases: List<String>,
    @SerializedName("place_of_birth")
    val placeOfBirth: String,
    @SerializedName("publisher")
    val publisher: String
)