package com.example.pokedexapp.ui.favoritos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedexapp.data.Pokemon
import com.example.pokedexapp.databinding.ItemFavoritosBinding

class AdapterFavoritos : ListAdapter<Pokemon, AdapterFavoritos.ViewHolder>(DIFF_CALLBACK) {
    var onClickListener: ((pokemonId: Int) -> Unit)? = null
    class ViewHolder(
        private val binding: ItemFavoritosBinding,
        private val onClickListener: ((pokemonId: Int) -> Unit)? = null

    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(x: Pokemon) {
            binding.tvNameFavorite.text = x.name
            binding.tvCandyPokemon.text = x.candy
            Glide.with(binding.root.context)
                .load(x.img)
                .into(binding.imagePokemon)

            binding.linearLayout2.setOnClickListener {
                onClickListener?.invoke(x.id)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Pokemon>() {
            override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                return oldItem.id == oldItem.id
            }

            override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                return oldItem == oldItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFavoritosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, onClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
