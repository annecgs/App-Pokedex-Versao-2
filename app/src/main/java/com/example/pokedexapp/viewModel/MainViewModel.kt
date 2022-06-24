package com.example.pokedexapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexapp.data.Pokemon
import com.example.pokedexapp.data.PokemonApiResult
import com.example.pokedexapp.repository.IPokemonsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class MainViewModel(private val IpokemonsRepository: IPokemonsRepository) : ViewModel() {
    private val _pokemonsItem = MutableLiveData<PokemonApiResult<List<Pokemon>>>()
    val pokemonItem: LiveData<PokemonApiResult<List<Pokemon>>> = _pokemonsItem
    var pokemonsFromApi: List<Pokemon> = ArrayList()

    fun getData() {
        viewModelScope.launch {
            _pokemonsItem.value = PokemonApiResult.Loading()
            pokemonsFromApi = withContext(Dispatchers.IO) {
                IpokemonsRepository.getPokedex()
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
                    // coinsFromApi = setIconUrl(coinsFromApi)
                    // coinsFromApi = getOnlyCrypto(coinsFromApi)
                }
                _pokemonsItem.value = PokemonApiResult.Success(pokemonsFromApi)
            } catch (e: Exception) {
                val coinResult = PokemonApiResult.Error<List<Pokemon>>(e)
                Log.d("PokemonResult", "PokemonCoin: $coinResult")
                _pokemonsItem.value = coinResult
            }
        }
    }
}
