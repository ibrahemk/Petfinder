package com.example.petfinder.Viewmodel

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.petfinder.Bean.Pet
import com.example.petfinder.Interface.Pet_profile_interface
import com.example.petfinder.databinding.FragmentPetProfileBinding
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class Pet_profile_viewmodel@Inject constructor(): ViewModel(), Pet_profile_interface {
    override fun handleui(
        pet: Pet,
        activity: FragmentActivity,
        binding: FragmentPetProfileBinding
    ) {
        pet.let {
            binding.Name.text = it.Name.ifEmpty { "N/A" }
            binding.Size.text = it.Size.ifEmpty { "N/A" }
            binding.Color.text = it.Color.ifEmpty { "N/A" }
            binding.Address.text = it.address.ifEmpty { "N/A" }
            if (it.url.isNotEmpty()){
               binding.website.visibility=View.VISIBLE
                binding.website.setOnClickListener {
                    val browserIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(pet.url)
                    )
                    activity.startActivity(browserIntent) }
            }else{
                binding.website.visibility=View.GONE
            }
        }

    }

    override fun setimage(
        pet: Pet,
        activity: FragmentActivity,
        binding: FragmentPetProfileBinding
    ) {
        Glide.with(activity)
            .asBitmap()
            .load(pet.image)
            .into(binding.PetImage)
    }

}