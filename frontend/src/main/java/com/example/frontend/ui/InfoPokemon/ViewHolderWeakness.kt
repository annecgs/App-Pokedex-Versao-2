package com.example.frontend.ui.InfoPokemon

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.frontend.R

class ViewHolderWeakness(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imgWeakness: ImageView = itemView.findViewById(R.id.icon_weakness)
    val nameWeakness: TextView = itemView.findViewById(R.id.name_weakness)
}