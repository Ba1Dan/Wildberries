package com.example.homework5dota.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Hero(
    @Json(name = "id")
    val id: Int,
    @Json(name = "localized_name")
    val name: String,
    @Json(name = "attack_type")
    val attackType: String,
    @Json(name = "icon")
    val icon: String,
    @Json(name = "img")
    val image: String,
    @Json(name = "base_health")
    val health: Int,
    @Json(name = "roles")
    val roles: List<String>
) : Parcelable