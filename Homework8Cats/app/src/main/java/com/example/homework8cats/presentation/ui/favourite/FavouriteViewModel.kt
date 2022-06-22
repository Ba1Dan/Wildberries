package com.example.homework8cats.presentation.ui.favourite

import androidx.lifecycle.*
import com.example.homework8cats.data.model.FavouriteCat
import com.example.homework8cats.data.model.ResultModel
import com.example.homework8cats.domain.repository.CatsRepository
import com.example.homework8cats.presentation.util.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FavouriteViewModel(
    private val catsRepository: CatsRepository
) : ViewModel() {

    private val mediatorLiveData = MediatorLiveData<State<List<FavouriteCat>>>()
//    private val _cat: MutableLiveData<State<List<FavouriteCat>>> = MutableLiveData()
    val cat: LiveData<State<List<FavouriteCat>>> = mediatorLiveData

    fun getFavouriteCat() {
        getFavouriteCatsFromDb()
        viewModelScope.launch(Dispatchers.IO) {
            val result = catsRepository.getFavouriteCats()
            handleResult(result)
        }
    }

    private fun getFavouriteCatsFromDb() {
        val catsLiveData = catsRepository.getFavouritesFromDb()
        mediatorLiveData.addSource(catsLiveData) { cats ->
            if (cats.isEmpty()) {
                mediatorLiveData.value = State.Loading
            } else {
                mediatorLiveData.value = State.Result(cats)
            }
        }
    }

    private suspend fun handleResult(result: ResultModel<List<FavouriteCat>>) {
        when (result) {
            is ResultModel.Failure -> {
                mediatorLiveData.postValue(State.Error(result.message))
            }
            is ResultModel.Success -> {
                    catsRepository.deleteAllFavouriteCats()
                    catsRepository.saveFavouriteCats(result.data)
//                _cat.postValue(State.Result(result.data))
            }
        }
    }
}