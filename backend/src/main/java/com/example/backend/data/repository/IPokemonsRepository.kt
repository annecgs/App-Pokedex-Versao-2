package com.example.pokedexapp.repository

import com.example.backend.data.dto.Pokemon

interface IPokemonsRepository {
    suspend fun getPokedex(): List<Pokemon>
}
