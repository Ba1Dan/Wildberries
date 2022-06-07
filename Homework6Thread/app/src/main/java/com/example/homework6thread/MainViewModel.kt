package com.example.homework6thread

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel() : ViewModel() {

    private val _number: MutableLiveData<String> = MutableLiveData()
    val number: LiveData<String> = _number

    fun updateNumber(newNumber: String) {
        _number.postValue(newNumber)
    }
}