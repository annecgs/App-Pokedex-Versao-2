package com.example.frontend.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.backend.data.dto.Pokemon
import com.example.backend.data.dto.PokemonApiResult
import com.example.pokedexapp.repository.IPokemonsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class MainViewModel(private val IpokemonsRepository: IPokemonsRepository) : ViewModel() {
    private val _pokemonsItem = MutableLiveData<PokemonApiResult<List<Pokemon>>>()
    val pokemonItem: LiveData<PokemonApiResult<List<Pokemon>>> = _pokemonsItem

    private val _pokemonSelected = MutableLiveData<Pokemon>()
    val pokemonSelected: LiveData<Pokemon> = _pokemonSelected

    var pokemonsFromApi: List<Pokemon> = ArrayList()

    // tratamento de erros
    var mensagem = ""

    fun setPokemon(pokemonId: Int) {
        viewModelScope.launch {
            _pokemonsItem.value = PokemonApiResult.Loading()
            try {
                if (pokemonsFromApi.isNullOrEmpty()) {
                    pokemonsFromApi = withContext(Dispatchers.IO) {
                        IpokemonsRepository.getPokedex()
                    }
                }
                _pokemonSelected.value = pokemonsFromApi.find { it.id == pokemonId }
                _pokemonsItem.value = PokemonApiResult.Success(pokemonsFromApi)
            } catch (e: Exception) {
                val coinResult = PokemonApiResult.Error<List<Pokemon>>(e)
                _pokemonsItem.value = coinResult
            }
        }
    }

    fun getCoinsFromRetrofit() {
        viewModelScope.launch {
            _pokemonsItem.value = PokemonApiResult.Loading()
            try {
                if (pokemonsFromApi.isNullOrEmpty()) {
                    pokemonsFromApi = withContext(Dispatchers.IO) {
                        IpokemonsRepository.getPokedex()
                    }
                }
                _pokemonsItem.value = PokemonApiResult.Success(pokemonsFromApi)
            } catch (e: Exception) {
                val pokemonResult = PokemonApiResult.Error<List<Pokemon>>(e)
                Log.d("PokemonResult", "PokemonCoin: $pokemonResult")
                _pokemonsItem.value = pokemonResult
            }
        }
    }

    fun setFavorite(isFavorite: Boolean) {
        viewModelScope.launch {
            _pokemonSelected.value?.isFavorite = isFavorite
        }
    }
}
