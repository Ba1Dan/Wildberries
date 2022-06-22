package com.example.homework7hero.data.model

import com.google.gson.annotations.SerializedName

class ResultObject(
    @SerializedName("results")
    val result: List<Hero>
)


