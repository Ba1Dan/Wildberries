package com.example.homework5cats.presentation.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework5cats.data.model.*
import com.example.homework5cats.domain.usecase.GetCatUseCase
import com.example.homework5cats.domain.usecase.SaveImageInFavouritesUseCase
import com.example.homework5cats.presentation.util.State
import com.example.homework8cats.presentation.util.NetworkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getCatUseCase: GetCatUseCase,
    private val saveImageInFavouritesUseCase: SaveImageInFavouritesUseCase,
    private val networkManager: NetworkManager
) : ViewModel() {

    private val _cat: MutableLiveData<State<Cat>> = MutableLiveData()
    val cat: LiveData<State<Cat>> = _cat

    private var catId: String? = null

    fun getCat() {
        _cat.value = State.Loading
        if (networkManager.isConnected().value) {
            viewModelScope.launch(Dispatchers.IO) {
                val result = getCatUseCase.execute()
                handleResult(result)
            }
        } else {
            _cat.postValue(State.Error("Нет интернета"))
        }
    }

    fun saveImageInFavourites() {
        if (networkManager.isConnected().value) {
            if (catId != null) {
                viewModelScope.launch(Dispatchers.IO) {
                    saveImageInFavouritesUseCase.execute(FavouriteModel(catId!!))
                }
            }
        } else {
            _cat.postValue(State.Error("Нет интернета"))
        }
    }

    private fun handleResult(result: ResultModel<List<Cat>>) {
        when (result) {
            is ResultModel.Failure -> {
                Log.d("handleResult", result.message.toString())
                _cat.postValue(State.Error(result.message))
            }
            is ResultModel.Success -> {
                val cat = result.data[0]
                catId = cat.id
                _cat.postValue(State.Result(cat))
            }
        }
    }
}