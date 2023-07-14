package com.example.frontend.ui.InfoPokemon

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.backend.data.dto.Pokemon
import com.example.frontend.R
import com.example.frontend.databinding.FragmentInfoBinding
import com.example.frontend.ui.activity.MainActivity
import com.example.frontend.ui.viewModel.MainViewModel
import com.example.frontend.utils.Helpers


class InfoFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels() { Helpers.getMainViewModelFactory() }

    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.hide()
        val root: View = binding.root

        binding.toolbarDetails.setNavigationOnClickListener(View.OnClickListener {
            (activity as MainActivity).onBackPressed()
        })

        setupAdapter()

        return root
    }

    private fun setupAdapter() {

        getData()
    }

    private fun getData() {
        viewModel.pokemonSelected.observe(viewLifecycleOwner) { pokemon ->
            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return@observe

            binding.addFavorite.setOnClickListener {
                if(pokemon.isFavorite){
                    sharedPref.edit().remove(pokemon.name).apply()
                    configFavorite(pokemon)

                }else{
                    sharedPref.edit().putString(pokemon.name,pokemon.name).apply()
                    configFavorite(pokemon)
                }

            }

            getHeight(pokemon)
            getWeight(pokemon)
            getCandy(pokemon)
            getInfoEgg(pokemon)
            configFavorite(pokemon)
            configEvolution(pokemon)
            configWeaknness(pokemon)
            configType(pokemon)

            Glide.with(binding.root.context)
                .load(pokemon.img)
                .into(binding.circleImageView)

        }
    }

    private fun configType(pokemon: Pokemon){
        if(!pokemon.type.isNullOrEmpty()){
            val layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )

            var adapter: AdapterType = AdapterType(pokemon.type)

            binding.listTypeInfo.layoutManager = layoutManager
            binding.listTypeInfo.adapter = adapter
        } else{

            //binding.textEmptyWeakness.visibility = View.VISIBLE
            binding.listTypeInfo.visibility = View.GONE
        }
    }

    private fun configWeaknness(pokemon: Pokemon){
        if(!pokemon.weaknesses.isNullOrEmpty()){
            val layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )

            var adapter: AdapterWeakness = AdapterWeakness(pokemon.weaknesses)

            binding.listWeakness.layoutManager = layoutManager
            binding.listWeakness.adapter = adapter
        } else{

            binding.textEmptyWeakness.visibility = View.VISIBLE
            binding.listWeakness.visibility = View.GONE
        }
    }

    private fun configEvolution(pokemon: Pokemon){
        if(!pokemon.next_evolution.isNullOrEmpty()){
            val layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )

            var adapter: AdapterEvolution = AdapterEvolution(pokemon.next_evolution)

            binding.listEvolution.layoutManager = layoutManager
            binding.listEvolution.adapter = adapter
        } else{

            binding.textEmptyList.visibility = View.VISIBLE
            binding.listEvolution.visibility = View.GONE
        }
    }

    private fun getHeight(pokemon: Pokemon){
        binding.valueHeight.text = pokemon.height
    }

    private fun getWeight(pokemon: Pokemon){
        binding.valueWeight.text = pokemon.weight
    }

    private fun getCandy(pokemon: Pokemon){
        binding.descriptionCandy.text = pokemon.candy
        binding.valueCandy.text = pokemon.candy_count.toString()
    }

    private fun getInfoEgg(pokemon: Pokemon){
        binding.valueDistance.text = pokemon.egg
        binding.valueTime.text = pokemon.spawn_time
        binding.valueChannge.text = pokemon.spawn_chance.toString()
    }

    private fun configFavorite(pokemon: Pokemon){
        if(pokemon.isFavorite){
            binding.addFavorite.text = "Remover"
            binding.iconFavorite.setImageResource(R.drawable.baseline_favorite_24_full_red)
        } else{
            binding.addFavorite.text = "Adicionar"
            binding.iconFavorite.setImageResource(R.drawable.baseline_favorite_border_24_red)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
