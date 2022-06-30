package com.example.pokedexapp.repository

import com.example.backend.data.dto.Pokemon
import com.example.pokedexapp.network.IPokemonsClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonsRepository(private val pokemonsClient: IPokemonsClient) : IPokemonsRepository {
    override suspend fun getPokedex(): List<Pokemon> {
        return withContext(Dispatchers.IO) {
            pokemonsClient.getPokemon().body()!!.pokemon
        }
    }
}
