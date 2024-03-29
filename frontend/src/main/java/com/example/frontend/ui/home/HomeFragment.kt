package com.example.frontend.ui.home

import android.content.Context
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
import com.example.frontend.R
import com.example.frontend.databinding.FragmentHomeBinding
import com.example.frontend.ui.InfoPokemon.InfoFragment
import com.example.frontend.ui.error.ErrorFragment
import com.example.frontend.utils.Helpers
import com.example.frontend.ui.viewModel.MainViewModel
import com.example.backend.data.dto.Pokemon
import com.example.backend.data.dto.PokemonApiResult
import kotlinx.android.synthetic.main.title_main.view.*

class HomeFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels() { Helpers.getMainViewModelFactory() }

    private var _binding: FragmentHomeBinding? = null
    private lateinit var adapterHome: AdapterHome
    private val binding get() = _binding!!
    private lateinit var errorFragment: ErrorFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.includeDate.tvMainDate.text = Helpers.getCalendarDate()
        //binding.includeDate.tv_mainDate.text = Helpers.getCalendarDate()
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

    private fun setlistQueryAdapter(newList: MutableList<Pokemon>) = if (newList.isNotEmpty()) {
        setListAdapter(newList)
        binding.widgetListEmpty.visibility = View.GONE
        binding.rvHome.visibility = View.VISIBLE
        //binding.include2.root.visibility = View.VISIBLE
        binding.include2.root.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
    } else {
        binding.rvHome.visibility = View.GONE
        binding.include2.root.visibility = View.GONE
        //binding.include2.root.visibility = View.VISIBLE
        binding.widgetListEmpty.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
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
                    //binding.include2.root.visibility = View.VISIBLE
                    binding.include2.root.visibility = View.VISIBLE

                    val sharedPref =
                        activity?.getPreferences(Context.MODE_PRIVATE) ?: return@observe

                    (listPokemons.data as List<Pokemon>).forEach { pokemon ->
                        if (sharedPref.all.containsKey(pokemon.name)) pokemon.isFavorite = true
                    }
                    setListAdapter(listPokemons.data as List<Pokemon>)
                    setupSearchView(listPokemons.data as List<Pokemon>)
                }
                is PokemonApiResult.Error<*> -> {
                    binding.progressBar.visibility = View.GONE
                    errorFragment = ErrorFragment()
                    replaceFragment(ErrorFragment())
                    //viewModel.mensagem = listPokemons.throwable.message.toString()
                    viewModel.mensagem = listPokemons.throwable.message.toString()
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
