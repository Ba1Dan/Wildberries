package com.example.homework5hero.data.model

import com.google.gson.annotations.SerializedName

class ResultObject(
    @SerializedName("results")
    val result: List<Hero>
)


