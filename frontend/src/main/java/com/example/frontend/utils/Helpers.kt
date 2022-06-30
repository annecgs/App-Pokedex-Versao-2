package com.example.frontend.utils

import com.example.frontend.ui.viewModel.MainViewModelFactory
import com.example.backend.data.dto.Pokemon
import com.example.pokedexapp.network.IPokemonsClient
import com.example.pokedexapp.network.RetrofitInstance
import com.example.pokedexapp.repository.PokemonsRepository
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

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

        fun getCalendarDate(): String {
            return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                val current = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
                current.format(formatter)
            } else {
                SimpleDateFormat("d MMM yyyy", Locale("pt-BR", "Brazil", "")).format(Date())
            }
        }
    }
}
