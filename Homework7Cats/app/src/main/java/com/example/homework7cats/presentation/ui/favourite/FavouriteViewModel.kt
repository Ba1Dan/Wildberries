package com.example.homework7cats.presentation.ui.favourite

import androidx.lifecycle.*
import com.example.homework7cats.data.model.FavouriteCat
import com.example.homework7cats.data.model.ResultModel
import com.example.homework7cats.domain.repository.CatsRepository
import com.example.homework7cats.presentation.util.NetworkManager
import com.example.homework7cats.presentation.util.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class FavouriteViewModel @Inject constructor(
    private val catsRepository: CatsRepository,
    private val networkManager: NetworkManager
) : ViewModel() {

    private val mediatorLiveData = MediatorLiveData<State<List<FavouriteCat>>>()
    val cat: LiveData<State<List<FavouriteCat>>> = mediatorLiveData

    fun getFavouriteCat() {
        getFavouriteCatsFromDb()
        if (networkManager.isConnected().value) {
            viewModelScope.launch(Dispatchers.IO) {
                val result = catsRepository.getFavouriteCats()
                handleResult(result)
            }
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
            }
        }
    }
}