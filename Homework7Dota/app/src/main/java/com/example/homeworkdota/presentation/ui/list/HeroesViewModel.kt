package com.example.homeworkdota.presentation.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworkdota.data.model.Hero
import com.example.homeworkdota.data.model.ResultModel
import com.example.homeworkdota.domain.usecase.GetHeroesFromFileUseCase
import com.example.homeworkdota.domain.usecase.GetHeroesUseCase
import com.example.homeworkdota.presentation.util.State
import kotlinx.coroutines.launch

class HeroesViewModel(
    private val getHeroesUseCase: GetHeroesUseCase,
    private val getHeroesFromFileUseCase: GetHeroesFromFileUseCase
) : ViewModel() {

    private val _heroes: MutableLiveData<State<List<Hero>>> = MutableLiveData()
    val heroes: LiveData<State<List<Hero>>> = _heroes

    fun getHeroes() {
        _heroes.value = State.Loading
        viewModelScope.launch {
            //Берем данные из файла
            val data = getHeroesFromFileUseCase.execute()
            if (data.isNotEmpty()) {
                _heroes.postValue(State.Result(data))
            }
//          Берем свежие данные с сервера
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