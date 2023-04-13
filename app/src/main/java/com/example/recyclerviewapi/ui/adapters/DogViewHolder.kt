package com.example.recyclerviewapi.ui.adapters

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewapi.databinding.ItemDogBinding
import com.squareup.picasso.Picasso

class DogViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemDogBinding.bind(view)

    fun bind(message: String,clickListener: (String, Int) -> Unit) {
        binding.tvDogs.text = message

//   envia Toast con el nombre de la raza que clickeamos
        binding.tvDogs.setOnClickListener {  clickListener(message, binding.tvDogs.id) }
        binding.cardbreed.setOnClickListener { clickListener(message,binding.cardbreed.id) }

    }


}