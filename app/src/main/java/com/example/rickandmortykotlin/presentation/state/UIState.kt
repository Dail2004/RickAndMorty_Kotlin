package com.example.rickandmortykotlin.presentation.state

sealed class UIState<T>(val  massage: String? = null) {
    class Loading<T> : UIState<T>()
    class Error<T>(error: String) : UIState<T>(massage = error)
    class Success<T>(val data: T) : UIState<T>()
}