package com.example.frontend.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.frontend.databinding.PokemonItemBinding
import com.example.backend.data.dto.Pokemon

class AdapterHome : ListAdapter<Pokemon, AdapterHome.ViewHolder>(DIFF_CALLBACK) {
    var onClickListener: ((pokemonId: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PokemonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, onClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    class ViewHolder(
        private val binding: PokemonItemBinding,
        private val onClickListener: ((pokemonId: Int) -> Unit)? = null
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(x: Pokemon) {
            binding.nomePokemon.text = x.name
            Glide.with(binding.root.context)
                .load(x.img)
                .into(binding.imagePokemon)

            binding.root.setOnClickListener {
                onClickListener?.invoke(x.id)
            }
        }

    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Pokemon>() {
            override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                return oldItem == newItem
            }
        }
    }
}
