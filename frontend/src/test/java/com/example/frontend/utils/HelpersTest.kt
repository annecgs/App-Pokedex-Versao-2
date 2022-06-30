package com.example.pokedexapp.utils

import com.example.backend.data.dto.Pokemon
import com.example.pokedexapp.viewModel.mockPokemon
import org.junit.Assert
import org.junit.Test

class HelpersTest {
    @Test
    fun `Filter query list is successful`() {
        // Given
        val list = mockPokemon
        val expectedList = mockPokemon[0] // Bulbasaur

        // When
        val result = Helpers.FilterListQuery("Bulbasaur", list)[0]

        // Them
        Assert.assertSame(expectedList, result)
    }

    @Test
    fun `When filterQueryList return a empty list`() {
        // Given
        val list = mockPokemon
        val expectedList = emptyList<Pokemon>()

        // When
        val result = Helpers.FilterListQuery("Nada", list) // No have pokemon "Nada" in mockPokemonList

        // Them
        Assert.assertSame(expectedList.size, result.size)
    }
}