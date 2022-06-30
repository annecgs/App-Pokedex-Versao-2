package com.example.frontend.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokedexapp.repository.IPokemonsRepository

class MainViewModelFactory(private val ipokemonsRepository: IPokemonsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(ipokemonsRepository) as T
    }
}
