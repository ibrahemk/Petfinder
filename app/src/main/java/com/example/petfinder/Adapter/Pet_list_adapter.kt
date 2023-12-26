package com.example.petfinder.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.petfinder.Bean.Pet
import com.example.petfinder.View_holder.Pet_list_holder
import com.example.petfinder.Viewmodel.Pet_list_viewmodel
import com.example.petfinder.databinding.PetRowBinding


class Pet_list_adapter(var list:ArrayList<Pet>,var model:Pet_list_viewmodel): RecyclerView.Adapter<Pet_list_holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Pet_list_holder {
        return Pet_list_holder(PetRowBinding.inflate(LayoutInflater.from(parent.context), parent, false), model)

    }

    override fun getItemCount(): Int {
  return list.size
    }

    override fun onBindViewHolder(holder: Pet_list_holder, position: Int) {
        holder.handlerow(list[position])
    }
}