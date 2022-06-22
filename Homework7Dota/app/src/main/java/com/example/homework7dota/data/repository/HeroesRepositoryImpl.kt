package com.example.homework7dota.data.repository

import com.example.homework7dota.data.db.LocalDataSource
import com.example.homework7dota.data.model.Hero
import com.example.homework7dota.data.model.ResultModel
import com.example.homework7dota.data.network.RemoteDataSource
import com.example.homework7dota.domain.repository.HeroesRepository
import com.squareup.moshi.JsonAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HeroesRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val adapter: JsonAdapter<List<Hero>>,
    private val localDataSource: LocalDataSource
) : HeroesRepository {

    override suspend fun getHeroes(): ResultModel<List<Hero>> = withContext(Dispatchers.IO) {
        val response = remoteDataSource.getHeroes()
        val json = response.body?.string()
            ?: return@withContext ResultModel.Failure("json is null")

        val cards: List<Hero>? = adapter.fromJson(json)

        if (cards == null) {
            return@withContext ResultModel.Failure("cards is null")
        } else {
            saveData(cards)
            return@withContext ResultModel.Success(cards)
        }
    }

    private fun saveData(data: List<Hero>) {
        localDataSource.writeToFile(adapter.toJson(data))
    }

    override suspend fun readDataFromFile(): List<Hero> = withContext(Dispatchers.IO) {
        val json = localDataSource.readFromFile()
        if (json.isEmpty()) return@withContext listOf()
        val data = adapter.fromJson(json)
        return@withContext data ?: listOf()
    }
}