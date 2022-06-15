package com.example.homework5hero.presentation

sealed class State<out T> {

    class Result<T>(val data: T) : State<T>()
    class Error(val message: String?) : State<Nothing>()
    object Loading : State<Nothing>()
}