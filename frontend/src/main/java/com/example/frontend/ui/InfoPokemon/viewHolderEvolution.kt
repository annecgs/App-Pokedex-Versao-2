package com.example.frontend.ui.InfoPokemon

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.frontend.R

class viewHolderEvolution(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imgEvolution: ImageView = itemView.findViewById(R.id.icon_evolution)
    val nameEvolution: TextView = itemView.findViewById(R.id.name_evolution)
}