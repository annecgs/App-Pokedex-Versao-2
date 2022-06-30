package com.example.pokedexapp.viewModel

import com.example.backend.data.dto.Pokemon
import com.example.pokedexapp.repository.IPokemonsRepository

class IPokemonsClientFake(val string: String) : IPokemonsRepository {
    override suspend fun getPokedex(): List<Pokemon> {
        return if (string == "OK-200") {
            mockPokemon
        } else if (string == "ERRO-Generic") {
            throw Throwable(string)
        } else listOf()
    }
}

val mockPokemon = listOf(
    Pokemon(
        "69",
        "Bulbasaur Candy",
        25,
        "2 km",
        "0.71 m",
        1,
        "http://www.serebii.net/pokemongo/pokemon/001.png",
        listOf(1.58), "Bulbasaur", listOf(), "001", 0.69, "20:00", listOf("Grass", "Poison"),
        listOf("Fire", "Ice", "Flying", "Psychic"), "6.9 kg", true
    )
)
