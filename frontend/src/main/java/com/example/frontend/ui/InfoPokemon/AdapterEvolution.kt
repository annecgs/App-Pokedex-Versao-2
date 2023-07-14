package com.example.frontend.ui.InfoPokemon

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.backend.data.dto.NextEvolution
import com.example.frontend.R

class AdapterEvolution(listEvolution: List<NextEvolution>) : RecyclerView.Adapter<viewHolderEvolution>() {
    var list : List<NextEvolution> = listEvolution

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): viewHolderEvolution {
        return viewHolderEvolution(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_evolution,parent,false)
        )
    }

    override fun getItemCount(): Int  = list.size

    override fun onBindViewHolder(holder: viewHolderEvolution, position: Int) {
        var aux = list[position].num
        var url = "http://www.serebii.net/pokemongo/pokemon/$aux.png"
        Glide.with(holder.itemView.context)
            .load(url)
            .into(holder.imgEvolution)

        holder.nameEvolution.text = list[position].name
    }
}