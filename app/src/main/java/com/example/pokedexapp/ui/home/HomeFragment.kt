package com.example.pokedexapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedexapp.R
import com.example.pokedexapp.data.Pokemon
import com.example.pokedexapp.data.PokemonApiResult
import com.example.pokedexapp.databinding.FragmentHomeBinding
import com.example.pokedexapp.ui.InfoPokemon.InfoFragment
import com.example.pokedexapp.utils.Helpers
import com.example.pokedexapp.viewModel.MainViewModel

class HomeFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels() { Helpers.getMainViewModelFactory() }

    private var _binding: FragmentHomeBinding? = null
    private lateinit var adapterHome: AdapterHome
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.includeDate.tvMainDate.text = Helpers.getCalendarDate()
        setupUi()
        return root
    }

    private fun setupUi() {
        configAdapter()
    }

    private fun configAdapter() {
        adapterHome = AdapterHome()
        binding.rvHome.adapter = adapterHome

        getData()
        goToFirstItemInRecyclerView()
    }

    private fun setupSearchView(list: List<Pokemon>) {
        var newList: MutableList<Pokemon> = ArrayList()
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchView.clearFocus()
                newList = Helpers.FilterListQuery(query, list)
                setlistQueryAdapter(newList)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newList = Helpers.FilterListQuery(newText, list)
                setlistQueryAdapter(newList)
                return true
            }
        })
    }

    private fun setlistQueryAdapter(newList: MutableList<Pokemon>) {
        if (newList.isNotEmpty()) {
            setListAdapter(newList)
            // binding.widgetListEmpty.visibility = View.GONE
            binding.rvHome.visibility = View.VISIBLE
            binding.include2.root.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
        } else {
            binding.rvHome.visibility = View.GONE
            binding.include2.root.visibility = View.GONE
            // binding.widgetListEmpty.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun getData() {
        viewModel.getCoinsFromRetrofit()
        viewModel.pokemonItem.observe(viewLifecycleOwner) { listPokemons ->
            when (listPokemons) {
                is PokemonApiResult.Loading<*> -> {
                    Log.d("INFO", "Loading")
                }
                is PokemonApiResult.Success<*> -> {
                    Log.d("INFO", "Success: ${listPokemons.data}")
                    binding.progressBar.visibility = View.GONE
                    binding.rvHome.visibility = View.VISIBLE
                    binding.include2.root.visibility = View.VISIBLE
                    // binding.rvMainCoins.visibility = View.VISIBLE
                   /* val sharedPref =
                        activity?.getPreferences(Context.MODE_PRIVATE) ?: return@observe

                    (listCoins.data as List<CoinItem>).forEach { coin ->
                        if (sharedPref.all.containsKey(coin.asset_id)) coin.isFavorite = true
                    }*/
                    setListAdapter(listPokemons.data as List<Pokemon>)
                    setupSearchView(listPokemons.data as List<Pokemon>)
                }
                is PokemonApiResult.Error<*> -> {
                    binding.progressBar.visibility = View.GONE
                    // errorFragment = ErrorFragment()
                    //  replaceFragment(ErrorFragment())
                    // viewModel.mensagem = listCoins.throwable.message.toString()
                    Log.d("INFO", "Error.cause: ${listPokemons.throwable.cause}")
                    Log.d("INFO", "Error: $listPokemons")
                    Log.d("INFO", "Error.message: ${listPokemons.throwable.message}")
                }
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapterHome.onClickListener = { pokemonId ->
            viewModel.setPokemon(pokemonId)
            replaceFragment(InfoFragment())
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = activity?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.nav_fragment, fragment)
        fragmentTransaction?.addToBackStack(null)
        fragmentTransaction?.commit()
    }

    private fun goToFirstItemInRecyclerView() {
        val linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvHome.layoutManager = linearLayoutManager
        binding.rvHome.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (linearLayoutManager.findFirstVisibleItemPosition() == 0) {
                    binding.fab.visibility = View.GONE
                } else binding.fab.visibility = View.VISIBLE
            }
        })
        binding.fab.setOnClickListener {
            binding.rvHome.scrollToPosition(0)
        }
    }

    private fun setListAdapter(list: List<Pokemon>) {
        adapterHome.submitList(list)
        adapterHome.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
