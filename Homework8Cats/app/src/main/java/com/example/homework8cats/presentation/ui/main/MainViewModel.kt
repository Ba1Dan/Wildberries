package com.example.homework8cats.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework8cats.data.model.*
import com.example.homework8cats.domain.usecase.GetCatUseCase
import com.example.homework8cats.domain.usecase.SaveImageInFavouritesUseCase
import com.example.homework8cats.presentation.util.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val getCatUseCase: GetCatUseCase,
    private val saveImageInFavouritesUseCase: SaveImageInFavouritesUseCase
) : ViewModel() {

    private val _cat: MutableLiveData<State<Cat>> = MutableLiveData()
    val cat: LiveData<State<Cat>> = _cat

    private val _saveCat: MutableLiveData<ResponseModel> = MutableLiveData()
    val saveCat: LiveData<ResponseModel> = _saveCat

    private var catId: String? = null

    fun getCat() {
        _cat.value = State.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val result = getCatUseCase.execute()
            handleResult(result)
        }
    }

    fun saveImageInFavourites() {
        if (catId != null) {
                viewModelScope.launch(Dispatchers.IO) {
                    val responseModel = saveImageInFavouritesUseCase.execute(FavouriteModel(catId!!))

                    responseModel?.let {
                        _saveCat.postValue(it)
                    }
                }
        } else {
            _saveCat.value = ResponseModel(0, "Failed")
        }
    }

    private fun handleResult(result: ResultModel<List<Cat>>) {
        when (result) {
            is ResultModel.Failure -> {
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