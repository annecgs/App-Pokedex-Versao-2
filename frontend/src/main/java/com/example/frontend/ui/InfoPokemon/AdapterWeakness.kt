package com.example.frontend.ui.InfoPokemon

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.frontend.R

class AdapterWeakness(listWeakness: List<String>) : RecyclerView.Adapter<ViewHolderWeakness>() {
    var list : List<String> = listWeakness

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderWeakness {
        return ViewHolderWeakness(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_weakness,parent,false)
        )
    }

    override fun getItemCount(): Int  = list.size

    override fun onBindViewHolder(holder: ViewHolderWeakness, position: Int) {
        holder.nameWeakness.text = list[position]

        when(list[position]){
            "Normal"-> {holder.imgWeakness.setImageResource(R.drawable.type_normal)}
            "Fire" -> {holder.imgWeakness.setImageResource(R.drawable.type_fire)}
            "Water" -> {holder.imgWeakness.setImageResource(R.drawable.type_water)}
            "Grass" -> {holder.imgWeakness.setImageResource(R.drawable.type_grass)}
            "Ground" -> {holder.imgWeakness.setImageResource(R.drawable.type_ground)}
            "Sand" -> {holder.imgWeakness.setImageResource(R.drawable.type_sand)}
            "Rock" -> {holder.imgWeakness.setImageResource(R.drawable.type_rock)}
            "Flying" -> {holder.imgWeakness.setImageResource(R.drawable.type_flying)}
            "Ice" -> {holder.imgWeakness.setImageResource(R.drawable.type_ice)}
            "Animal" -> {holder.imgWeakness.setImageResource(R.drawable.type_animal)}
            "Bug"-> {holder.imgWeakness.setImageResource(R.drawable.type_bug)}
            "Electric" -> {holder.imgWeakness.setImageResource(R.drawable.type_electric)}
            "Steel" -> {holder.imgWeakness.setImageResource(R.drawable.type_steel)}
            "Fighting" -> {holder.imgWeakness.setImageResource(R.drawable.type_fligthing)}
            "Speedy" -> {holder.imgWeakness.setImageResource(R.drawable.type_speedy)}
            "Poison" -> {holder.imgWeakness.setImageResource(R.drawable.type_position)}
            "Dark" -> {holder.imgWeakness.setImageResource(R.drawable.type_dark)}
            "Psychic" -> {holder.imgWeakness.setImageResource(R.drawable.type_phisichic)}
            "Sound" -> {holder.imgWeakness.setImageResource(R.drawable.type_sound)}
            "Ghost" -> {holder.imgWeakness.setImageResource(R.drawable.type_ghost)}
            "Fairy" -> {holder.imgWeakness.setImageResource(R.drawable.type_fairy)}
            "Dragon" -> {holder.imgWeakness.setImageResource(R.drawable.type_dragon)}
            else -> {}
        }
    }
}