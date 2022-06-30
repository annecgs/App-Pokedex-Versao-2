package com.example.pokedexapp.repository

import com.example.pokedexapp.data.Pokemon

interface IPokemonsRepository {
    suspend fun getPokedex(): List<Pokemon>
}
