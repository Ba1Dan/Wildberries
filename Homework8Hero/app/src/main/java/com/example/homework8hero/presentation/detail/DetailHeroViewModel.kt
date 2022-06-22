package com.example.homework8hero.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework8hero.data.model.HeroDetail
import com.example.homework8hero.domain.usecase.GetInfoHero
import com.example.homework8hero.presentation.util.NetworkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailHeroViewModel @Inject constructor(private val getInfoHero: GetInfoHero, private val networkManager: NetworkManager) : ViewModel() {

    private val _hero: MutableLiveData<HeroDetail> = MutableLiveData()
    val hero: LiveData<HeroDetail> = _hero

    fun getHeroById(id: Int) {
        if(networkManager.isConnected().value) {
            viewModelScope.launch(Dispatchers.IO) {
                val hero = getInfoHero.execute(id)
                _hero.postValue(hero)

            }
        }
    }
}