package com.example.homework8hero.data.model

import com.google.gson.annotations.SerializedName

class ResultObject(
    @SerializedName("results")
    val result: List<Hero>
)


