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
        return client.get {
            url(URL_SEARCH)
            header(HEADER_NAME, API_KEY)
        }
    }

    override suspend fun saveImageInFavourites(favourite: FavouriteModel): ResponseModel? {
        return try {
            client.post<ResponseModel> {
                url(URL_FAVOURITES)
                header(HEADER_NAME, API_KEY)
                body = favourite
            }
        } catch (e: Exception) {
            return null
        }
    }

    override suspend fun getFavouriteCats(): List<FavouriteCat> {
        return client.get {
            url(URL_FAVOURITES)
            header(HEADER_NAME, API_KEY)
        }
    }

    companion object {

        private const val BASE_URL = "https://api.thecatapi.com/v1"
        private const val URL_FAVOURITES = "$BASE_URL/favourites"
        private const val URL_SEARCH = "$BASE_URL/images/search"

        private const val HEADER_NAME = "x-api-key"
        private const val API_KEY = "46f227b0-75bb-48e2-bc68-c32059d0228a"
    }
}