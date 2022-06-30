package com.example.pokedexapp.network

import com.example.pokedexapp.data.Pokedex
import retrofit2.Response
import retrofit2.http.GET

interface IPokemonsClient {
    @GET("pokedex.json")
    // @SerializedName("pokemon")

    suspend fun getPokemon(): Response<Pokedex>

    /*suspend fun getPokemon(): List<Pokemon> {
        lateinit var teste: Response<Pokedex>
        val lista: List<Pokemon>
        lista = teste.body()!!.pokemon
        return lista
    }*/
}
