package com.example.homeworkdota.data.repository

import com.example.homeworkdota.data.model.Hero
import com.example.homeworkdota.data.model.ResultModel
import com.example.homeworkdota.data.network.RemoteDataSource
import com.example.homeworkdota.domain.repository.HeroesRepository
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HeroesRepositoryImpl(private val remoteDataSource: RemoteDataSource, private val moshi: Moshi) : HeroesRepository {

    override suspend fun getHeroes(): ResultModel<List<Hero>> = withContext(Dispatchers.IO){
        val response = remoteDataSource.getHeroes()
        val json = response.body?.string()
            ?:
            return@withContext ResultModel.Failure("json is null")

        val type = Types.newParameterizedType(
            MutableList::class.java,
            Hero::class.java
        )

        val adapter: JsonAdapter<List<Hero>> = moshi.adapter(type)
        val cards: List<Hero>? = adapter.fromJson(json)

        if (cards == null) {
            return@withContext ResultModel.Failure("cards is null")
        } else {
            return@withContext ResultModel.Success(cards)
        }
    }
}