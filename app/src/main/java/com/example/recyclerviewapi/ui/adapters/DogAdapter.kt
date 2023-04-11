package com.example.recyclerviewapi.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewapi.R

class DogAdapter(private val message:List<String>,
                 private val clickListener: (String, Int) -> Unit):RecyclerView.Adapter<DogViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DogViewHolder(layoutInflater.inflate(R.layout.item_dog,parent,false))
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val item: String = message [position]
        holder.bind(item,clickListener)

    }

    override fun getItemCount(): Int = message.size

    }
