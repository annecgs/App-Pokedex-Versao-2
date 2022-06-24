package com.example.pokedexapp.ui.dashboard

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.pokedexapp.databinding.FragmentInfoBinding
import com.example.pokedexapp.utils.Helpers
import com.example.pokedexapp.viewModel.MainViewModel

class InfoFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels() { Helpers.getMainViewModelFactory() }

    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupAdapter()

        binding.btnReturn.setOnClickListener {
            getActivity()?.onBackPressed()
        }

        return root
    }

    private fun setupAdapter() {

        getData()
    }

    private fun getData() {
        viewModel.pokemonSelected.observe(viewLifecycleOwner) { pokemon ->
            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return@observe
            if (sharedPref.all.containsKey(pokemon.name)) {
                binding.ivFavorite.visibility = View.VISIBLE
                binding.btnAddFavorite.text = "REMOVER"
            } else {
                binding.ivFavorite.visibility = View.GONE
                binding.btnAddFavorite.text = "ADICIONAR"
            }

            Log.d("CoinRecive", "onActivityCreated: $pokemon")
            //binding.tvCandy.text = pokemon.candy
            binding.tvPokemonNome.text = pokemon.name
            binding.tvAltura.text = pokemon.height
            binding.tvPeso.text = pokemon.weight
            binding.tvOvo.text = pokemon.egg

            Glide.with(binding.root.context)
                .load(pokemon.img)
                .into(binding.imageView)

            binding.btnAddFavorite.setOnClickListener {
                Log.d("Coin", "getData: $pokemon")
                when (pokemon.isFavorite) {
                    true -> { // Vai remover dos favoritos
                        sharedPref.edit().remove(pokemon.name).apply()
                        viewModel.setFavorite(false)
                        binding.btnAddFavorite.text = "ADICIONAR"
                        binding.ivFavorite.visibility = View.GONE
                    }
                    false -> { // Vai adicionar aos favoritos
                        sharedPref.edit().putString(pokemon.name, pokemon.name).apply()
                        viewModel.setFavorite(true)
                        binding.btnAddFavorite.text = "REMOVER"
                        binding.ivFavorite.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
