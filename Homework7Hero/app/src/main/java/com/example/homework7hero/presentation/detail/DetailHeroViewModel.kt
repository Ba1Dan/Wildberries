package com.example.homework7hero.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework7hero.data.model.HeroDetail
import com.example.homework7hero.domain.usecase.GetInfoHero
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailHeroViewModel(private val getInfoHero: GetInfoHero) : ViewModel() {

    private val _hero: MutableLiveData<HeroDetail> = MutableLiveData()
    val hero: LiveData<HeroDetail> = _hero

    fun getHeroById(id: Int) {

        viewModelScope.launch(Dispatchers.IO) {
            val hero = getInfoHero.execute(id)
            _hero.postValue(hero)

        }
    }
}