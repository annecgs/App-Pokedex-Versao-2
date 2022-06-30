package com.example.backend.data.dto

sealed class PokemonApiResult <T> {
    class Loading<T> : PokemonApiResult<T>()
    class Success<T>(val data: T) : PokemonApiResult<T>()
    class Error<T>(val throwable: Throwable) : PokemonApiResult<T>()
}
