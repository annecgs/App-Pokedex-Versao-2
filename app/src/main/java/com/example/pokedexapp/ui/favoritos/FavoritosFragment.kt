package com.example.pokedexapp.ui.notifications

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedexapp.R
import com.example.pokedexapp.data.Pokemon
import com.example.pokedexapp.data.PokemonApiResult
import com.example.pokedexapp.databinding.FragmentNotificationsBinding
import com.example.pokedexapp.ui.InfoPokemon.InfoFragment
import com.example.pokedexapp.utils.Helpers
import com.example.pokedexapp.viewModel.MainViewModel

class NotificationsFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels { Helpers.getMainViewModelFactory() }
    private lateinit var adapter: AdapterFavoritos
    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.include.tvMainDate.text = Helpers.getCalendarDate()

        setupAdapter()

        adapter.onClickListener = { pokemonId ->
            viewModel.setPokemon(pokemonId)
            replaceFragment(InfoFragment())
        }

        return root
    }

    private fun setupAdapter() {
        adapter = AdapterFavoritos()

        val layoutManager = GridLayoutManager(activity, 2)
        layoutManager.orientation = RecyclerView.HORIZONTAL

        binding.recyclerViewFavorites.layoutManager = layoutManager

        binding.recyclerViewFavorites.adapter = adapter

        viewModel.pokemonItem.observe(viewLifecycleOwner) { listPokemons ->
            getFavorites(listPokemons)
        }
    }




    private fun getFavorites(list: PokemonApiResult<List<Pokemon>>) {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        Log.d("ListFavorites", "getFavorites: ${sharedPref.all}")
        if (sharedPref.all.isEmpty()) binding.tvNoFavorites.visibility = View.VISIBLE else binding.tvNoFavorites.visibility = View.GONE
        val tempList: MutableList<Pokemon> = ArrayList()
        when (list) {
            is PokemonApiResult.Success -> {
                list.data.forEach {
                    if (sharedPref.all.contains(it.name)) {
                        tempList.add(it)
                    }
                }
            }

            is PokemonApiResult.Error -> {
                //errorFragment = ErrorFragment()
                //replaceFragment(ErrorFragment())
            }
        }

        Log.d(
            "ListFilter",
            "getFavorites: $tempList"
        )

        adapter.submitList(tempList)
    }




    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = activity?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.nav_fragment, fragment)
        fragmentTransaction?.addToBackStack(null)
        fragmentTransaction?.commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}