package com.example.homework7hero.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RequestManager {

    private const val BASE_URL = "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/"
    const val ACCESS_TOKEN = "3202191630032155"

    private val retrofit = Retrofit.Builder()
        .client(createClient())
        .baseUrl(BASE_URL)
        .addConverterFactory(createConverterFactory())
        .build()

    private fun createConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    private fun createClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    val service: HeroesApi = retrofit.create(HeroesApi::class.java)

}