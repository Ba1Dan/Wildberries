package com.example.homework5hero.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework5hero.data.model.Hero
import com.example.homework5hero.domain.repository.HeroesRepository
import com.example.homework5hero.presentation.util.NetworkManager
import com.example.homework5hero.presentation.util.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListHeroesViewModel @Inject constructor(
    private val heroesRepository: HeroesRepository,
    private val networkManager: NetworkManager
) : ViewModel() {

    private val _heroes: MutableLiveData<State<List<Hero>>> = MutableLiveData()
    val heroes: LiveData<State<List<Hero>>> = _heroes

    fun searchHeroes(query: String) {
        _heroes.value = State.Loading
        try {
            viewModelScope.launch(Dispatchers.IO) {
                if (networkManager.isConnected().value) {
                    val data = heroesRepository.searchHeroes(query)
                    _heroes.postValue(State.Result(data))
                } else {
                    _heroes.postValue(State.Result(listOf()))
                }
            }
        } catch (e: Exception) {
            _heroes.value = State.Error(e.message)
        }
    }
}