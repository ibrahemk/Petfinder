package com.example.petfinder.View_holder

import androidx.recyclerview.widget.RecyclerView
import com.example.petfinder.Bean.Pet_type
import com.example.petfinder.R
import com.example.petfinder.Viewmodel.Pet_list_viewmodel
import com.example.petfinder.databinding.TypeItemBinding

class Type_list_holder(val binding: TypeItemBinding, val model:Pet_list_viewmodel)
    : RecyclerView.ViewHolder(binding.root) {
        fun handle_item(type:Pet_type){
            type.let {
                binding.typename.text = type.type
                binding.main.setOnClickListener { select_type(type) }
                if (type.isSelected){
                    binding.typename.setTextColor(model.activity.getColor(R.color.white))
                    binding.main.setBackgroundColor(model.activity.getColor(R.color.black))
                }else{
                    binding.typename.setTextColor(model.activity.getColor(R.color.black))
                    binding.main.setBackgroundColor(model.activity.getColor(R.color.white))
                }
            }
        }
    fun select_type(type: Pet_type){
        type.isSelected=true
    }
}