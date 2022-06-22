package com.example.homework8hero.presentation.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework8hero.data.model.Hero
import com.example.homework8hero.domain.repository.HeroesRepository
import com.example.homework8hero.presentation.util.NetworkManager
import com.example.homework8hero.presentation.util.State
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
                //Берем данные из sharedPreferences
                val cache = heroesRepository.getHeroesFromLocal()
                _heroes.postValue(State.Result(cache))

                //Берем свежие данные с сервера
                if (networkManager.isConnected().value) {
                    val data = heroesRepository.searchHeroes(query)
                    _heroes.postValue(State.Result(data))
                } else {
                    _heroes.value = State.Error("Нет соединения с интернетом")
                }
            }
        } catch (e: Exception) {
            _heroes.value = State.Error(e.message)
        }

    }
}