package com.example.frontend.ui.InfoPokemon

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.frontend.R

class ViewHolderType(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imgType: ImageView = itemView.findViewById(R.id.icon_type)
    val nameType: TextView = itemView.findViewById(R.id.name_type)
}