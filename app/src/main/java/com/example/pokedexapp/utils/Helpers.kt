package com.example.pokedexapp.utils

import com.example.pokedexapp.data.Pokemon
import com.example.pokedexapp.network.IPokemonsClient
import com.example.pokedexapp.network.RetrofitInstance
import com.example.pokedexapp.repository.PokemonsRepository
import com.example.pokedexapp.viewModel.MainViewModelFactory
import java.util.ArrayList

class Helpers {
    companion object {
        fun getMainViewModelFactory(): MainViewModelFactory {
            val coinsClient: IPokemonsClient by lazy {
                RetrofitInstance.get().create(IPokemonsClient::class.java)
            }
            val coinsRepository = PokemonsRepository(coinsClient)
            return MainViewModelFactory(coinsRepository)
        }

        fun FilterListQuery(text: String?, list: List<Pokemon>): MutableList<Pokemon> {
            var newList: MutableList<Pokemon> = ArrayList()
            list.forEach {
                if (it.name.contains(text.toString(), true)
                    or it.candy.contains(text.toString(), false)
                ) {
                    newList.add(it)
                }
            }
            return newList
        }
    }
}