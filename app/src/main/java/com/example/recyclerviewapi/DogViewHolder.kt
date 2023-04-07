package com.example.recyclerviewapi

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewapi.databinding.ItemDogBinding
import com.squareup.picasso.Picasso

class DogViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemDogBinding.bind(view)

    fun bind(message: String) {
        Picasso.get().load(message).into(binding.ivDog)


    }

}