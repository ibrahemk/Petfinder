package com.example.petfinder.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.petfinder.Bean.Pet
import com.example.petfinder.Bean.Pet_type
import com.example.petfinder.View_holder.Type_list_holder
import com.example.petfinder.Viewmodel.Pet_list_viewmodel
import com.example.petfinder.databinding.TypeItemBinding

class Pet_type_adapter(var list:ArrayList<Pet_type>, var model: Pet_list_viewmodel): RecyclerView.Adapter<Type_list_holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Type_list_holder {

        return Type_list_holder(TypeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false), model)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Type_list_holder, position: Int) {
holder.handle_item(list[position])
    }
}