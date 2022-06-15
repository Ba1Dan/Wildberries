package com.example.homeworkdota.data.repository

import com.example.homeworkdota.data.db.LocalDataSource
import com.example.homeworkdota.data.model.Hero
import com.example.homeworkdota.data.model.ResultModel
import com.example.homeworkdota.data.network.RemoteDataSource
import com.example.homeworkdota.domain.repository.HeroesRepository
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HeroesRepositoryImpl(
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