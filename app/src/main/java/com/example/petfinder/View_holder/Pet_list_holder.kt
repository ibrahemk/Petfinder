package com.example.petfinder.View_holder

import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.petfinder.Bean.Pet
import com.example.petfinder.R
import com.example.petfinder.Viewmodel.Pet_list_viewmodel
import com.example.petfinder.databinding.PetRowBinding
import com.example.petfinder.fragment.Pet_profile_Fragment


class Pet_list_holder(val binding: PetRowBinding, val model: Pet_list_viewmodel)
    : RecyclerView.ViewHolder(binding.root)  {
    fun handlerow(pet: Pet){
        pet.let {
            binding.Name.text = it.Name.ifEmpty { "N/A" }
            binding.gender.text = it.Size.ifEmpty { "N/A" }
            binding.type.text = it.Color.ifEmpty { "N/A" }
            setimage(it)
            binding.row.setOnClickListener { open_product(pet = pet) }
        }


    }
    fun setimage(pet: Pet) {

        val myOptions: RequestOptions = RequestOptions()
            .override(200, 200)
            .fitCenter()
            .placeholder(R.drawable.no_image)
        Glide.with(model.activity)
            .asBitmap()
            .load(pet.smallimage)
            .apply(myOptions)
            .into(binding.Petimage)

    }
    fun open_product(pet: Pet) {
        model.activity.supportFragmentManager.beginTransaction()
            .replace(R.id.container, Pet_profile_Fragment.newInstance(pet), "product")
            .addToBackStack("product").commit()
    }
}