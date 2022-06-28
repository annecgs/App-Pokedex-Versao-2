package com.example.pokedexapp.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.pokedexapp.data.PokemonApiResult
import com.example.pokedexapp.test_utils.MainCoroutineRule
import com.example.pokedexapp.test_utils.getOrAwaitValue
import io.mockk.InternalPlatformDsl.toArray
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MainViewModelTest : TestCase() {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    lateinit var mainViewModelOk: MainViewModel
    lateinit var mainViewModelError: MainViewModel

    @Before
    fun setup() {
        mainViewModelOk = MainViewModel(
            // PokemonsRepository(
            IPokemonsClientFake("OK-200")
            // )
        )
        mainViewModelError = MainViewModel(
            // PokemonsRepository(
            IPokemonsClientFake("ERRO-Generic")
            // )
        )
    }

    @Test
    fun `Set ID of _coinSelected and return the same ID in coinSelected`() {
        // Given
        val id = 1

        // Then
        mainViewModelOk.setPokemon(id)
        val result = mainViewModelOk.pokemonSelected.getOrAwaitValue().id

        // When
        Assert.assertEquals(id, result)
    }

    @Test
    fun `When the list of coins isn't empty, not require other api call`() {
        // Given
        val list = mainViewModelOk.pokemonItem
        var result = true
        var result2 = true

        // Then
        mainViewModelOk.getCoinsFromRetrofit()
        when (list) {
            is PokemonApiResult.Success<*> -> {
                result = list.getOrAwaitValue().toArray().isEmpty() // Return False
            }
        }

        mainViewModelOk.getCoinsFromRetrofit()

        when (mainViewModelOk.pokemonItem) {
            is PokemonApiResult.Success<*> -> {
                result2 = list.getOrAwaitValue().toArray().isEmpty() // Return False
            }
        }

        // When
        Assert.assertEquals(result, result2)
    }

    @Test
    fun `When setFavorite set true`() {
        // Given
        val bulbasaur = mockPokemon[0]
        bulbasaur.isFavorite = true // Is favorite

        // When
        mainViewModelOk.getCoinsFromRetrofit()
        mainViewModelOk.setPokemon(1)
        mainViewModelOk.setFavorite(true)

        // Then

        Assert.assertEquals(
            bulbasaur.isFavorite,
            mainViewModelOk.pokemonSelected.getOrAwaitValue().isFavorite
        )
    }

    @Test
    fun `When setFavorite set false`() {
        // Given
        val bulbasaur = mockPokemon[0]
        bulbasaur.isFavorite = false // Is favorite

        // When
        mainViewModelOk.getCoinsFromRetrofit()
        mainViewModelOk.setPokemon(1)
        mainViewModelOk.setFavorite(false)

        // Then

        Assert.assertEquals(
            bulbasaur.isFavorite,
            mainViewModelOk.pokemonSelected.getOrAwaitValue().isFavorite
        )
    }
}
