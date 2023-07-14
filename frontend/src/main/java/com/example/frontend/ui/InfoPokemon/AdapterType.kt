package com.example.frontend.ui.InfoPokemon

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.frontend.R

class AdapterType(listType: List<String>) : RecyclerView.Adapter<ViewHolderType>() {
    var list : List<String> = listType

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderType {
        return ViewHolderType(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_type,parent,false)
        )
    }

    override fun getItemCount(): Int  = list.size

    override fun onBindViewHolder(holder: ViewHolderType, position: Int) {
        holder.nameType.text = list[position]
        when(list[position]){
            "Normal"-> {holder.imgType.setImageResource(R.drawable.type_normal)}
            "Fire" -> {holder.imgType.setImageResource(R.drawable.type_fire)}
            "Water" -> {holder.imgType.setImageResource(R.drawable.type_water)}
            "Grass" -> {holder.imgType.setImageResource(R.drawable.type_grass)}
            "Ground" -> {holder.imgType.setImageResource(R.drawable.type_ground)}
            "Sand" -> {holder.imgType.setImageResource(R.drawable.type_sand)}
            "Rock" -> {holder.imgType.setImageResource(R.drawable.type_rock)}
            "Flying" -> {holder.imgType.setImageResource(R.drawable.type_flying)}
            "Ice" -> {holder.imgType.setImageResource(R.drawable.type_ice)}
            "Animal" -> {holder.imgType.setImageResource(R.drawable.type_animal)}
            "Bug"-> {holder.imgType.setImageResource(R.drawable.type_bug)}
            "Electric" -> {holder.imgType.setImageResource(R.drawable.type_electric)}
            "Steel" -> {holder.imgType.setImageResource(R.drawable.type_steel)}
            "Fighting" -> {holder.imgType.setImageResource(R.drawable.type_fligthing)}
            "Speedy" -> {holder.imgType.setImageResource(R.drawable.type_speedy)}
            "Poison" -> {holder.imgType.setImageResource(R.drawable.type_position)}
            "Dark" -> {holder.imgType.setImageResource(R.drawable.type_dark)}
            "Psychic" -> {holder.imgType.setImageResource(R.drawable.type_phisichic)}
            "Sound" -> {holder.imgType.setImageResource(R.drawable.type_sound)}
            "Ghost" -> {holder.imgType.setImageResource(R.drawable.type_ghost)}
            "Fairy" -> {holder.imgType.setImageResource(R.drawable.type_fairy)}
            "Dragon" -> {holder.imgType.setImageResource(R.drawable.type_dragon)}
            else -> {}
        }
    }
}