package com.example.homework5hero.presentation.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework5hero.data.model.Hero
import com.example.homework5hero.domain.repository.HeroesRepository
import com.example.homework5hero.domain.usecase.SearchHeroesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListHeroesViewModel(private val searchHeroesUseCase: SearchHeroesUseCase) : ViewModel() {

    private val _heroes: MutableLiveData<List<Hero>> = MutableLiveData()
    val heroes: LiveData<List<Hero>> = _heroes

    fun searchHeroes(query: String) {

        try {
            viewModelScope.launch(Dispatchers.IO) {
                val data = searchHeroesUseCase.execute(query)
                Log.d("ListHeroesViewModel", "$data")
                _heroes.postValue(data)
            }
        } catch (e: Exception) {
            Log.d("ListHeroesViewModel", "An error has occurred")
        }
    }
}