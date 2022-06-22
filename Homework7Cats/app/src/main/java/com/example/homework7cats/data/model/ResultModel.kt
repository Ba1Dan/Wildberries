package com.example.homework7cats.data.model

sealed class ResultModel<out T> {

    class Success<T>(val data: T) : ResultModel<T>()
    class Failure(val message: String?) : ResultModel<Nothing>()

}