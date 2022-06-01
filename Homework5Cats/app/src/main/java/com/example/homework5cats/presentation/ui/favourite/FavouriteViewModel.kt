package com.example.homework5cats.presentation.ui.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework5cats.data.model.Cat
import com.example.homework5cats.data.model.FavouriteCat
import com.example.homework5cats.data.model.ResultModel
import com.example.homework5cats.domain.usecase.GetFavouriteCatsUseCase
import com.example.homework5cats.presentation.util.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouriteViewModel(private val getFavouriteCatsUseCase: GetFavouriteCatsUseCase) : ViewModel() {

    private val _cat: MutableLiveData<State<List<FavouriteCat>>> = MutableLiveData()
    val cat: LiveData<State<List<FavouriteCat>>> = _cat

    fun getFavouriteCat() {
        _cat.value = State.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val result = getFavouriteCatsUseCase.execute()
            handleResult(result)
        }
    }

    private fun handleResult(result: ResultModel<List<FavouriteCat>>) {
        when (result) {
            is ResultModel.Failure -> {
                _cat.postValue(State.Error(result.message))
            }
            is ResultModel.Success -> {
                _cat.postValue(State.Result(result.data))
            }
        }
    }
}