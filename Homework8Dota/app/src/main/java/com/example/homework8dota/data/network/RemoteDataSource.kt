package com.example.homework8dota.data.network

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response


class RemoteDataSource(private val okHttpClient: OkHttpClient) {

    fun getHeroes(): Response {
        val request: Request = Request.Builder().url(HERO_STATS).build()

        return okHttpClient.newCall(request).execute()
    }

    companion object {
        const val BASE_URL = "https://api.opendota.com"
        private const val HERO_STATS = "$BASE_URL/api/heroStats"
    }
}