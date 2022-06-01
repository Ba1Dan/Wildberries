package com.example.homework5cats.data.network

import com.example.homework5cats.data.model.Cat
import com.example.homework5cats.data.model.FavouriteCat
import com.example.homework5cats.data.model.FavouriteModel
import com.example.homework5cats.data.model.ResponseModel
import io.ktor.client.*
import io.ktor.client.request.*


class ApiServiceImpl(
    private val client: HttpClient
) : ApiService {

    override suspend fun getProducts(): List<Cat> {
        return try {
            client.get {
                url("https://api.thecatapi.com/v1/images/search")
                header("x-api-key", "46f227b0-75bb-48e2-bc68-c32059d0228a")
            }
        } catch (ex: Exception) {
            // 3xx - responses
            println("Error: $ex")
            emptyList()
        }
    }

    override suspend fun saveImageInFavourites(favourite: FavouriteModel): ResponseModel? {
        return try {
            client.post<ResponseModel> {
                url("https://api.thecatapi.com/v1/favourites")
                header("x-api-key", "46f227b0-75bb-48e2-bc68-c32059d0228a")
                body = favourite
            }
        } catch (e: Exception) {
            return null
        }
    }

    override suspend fun getFavouriteCats(): List<FavouriteCat> {
        return try {
            client.get {
                url("https://api.thecatapi.com/v1/favourites")
                header("x-api-key", "46f227b0-75bb-48e2-bc68-c32059d0228a")
            }
        } catch (ex: Exception) {
            emptyList()
        }
    }
}