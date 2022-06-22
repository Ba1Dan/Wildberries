package com.example.homework7hero.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework7hero.data.model.Hero
import com.example.homework7hero.domain.repository.HeroesRepository
import com.example.homework7hero.presentation.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListHeroesViewModel(private val heroesRepository: HeroesRepository) : ViewModel() {

    private val _heroes: MutableLiveData<State<List<Hero>>> = MutableLiveData()
    val heroes: LiveData<State<List<Hero>>> = _heroes

    fun searchHeroes(query: String) {
        _heroes.value = State.Loading
        try {
            viewModelScope.launch(Dispatchers.IO) {
                //Берем данные из sharedPreferences
                val cache = heroesRepository.getHeroesFromLocal()
                _heroes.postValue(State.Result(cache))

                //Берем свежие данные с сервера
                val data = heroesRepository.searchHeroes(query)
                _heroes.postValue(State.Result(data))
            }
        } catch (e: Exception) {
            _heroes.value = State.Error(e.message)
        }
    }
}