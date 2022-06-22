package com.example.homework5dota.presentation.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework5dota.data.model.Hero
import com.example.homework5dota.data.model.ResultModel
import com.example.homework5dota.domain.usecase.GetHeroesUseCase
import com.example.homework5dota.presentation.util.State
import kotlinx.coroutines.launch
import javax.inject.Inject

class HeroesViewModel @Inject constructor(private val getHeroesUseCase: GetHeroesUseCase) : ViewModel() {

    private val _heroes: MutableLiveData<State<List<Hero>>> = MutableLiveData()
    val heroes: LiveData<State<List<Hero>>> = _heroes

    fun getHeroes() {
        viewModelScope.launch {
            val result = getHeroesUseCase.execute()
            handleResult(result)
        }
    }

    private fun handleResult(result: ResultModel<List<Hero>>) {
        when (result) {
            is ResultModel.Failure -> {
                _heroes.postValue(State.Error(result.message))
            }
            is ResultModel.Success -> {
                val heroes = result.data
                _heroes.postValue(State.Result(heroes))
            }
        }
    }
}