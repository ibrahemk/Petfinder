package com.example.petfinder.Interface

import androidx.fragment.app.FragmentActivity
import com.example.petfinder.Bean.Pet
import com.example.petfinder.databinding.FragmentPetProfileBinding

interface Pet_profile_interface {
    fun handleui(pet: Pet, activity: FragmentActivity, binding: FragmentPetProfileBinding)
    fun setimage(pet: Pet, activity: FragmentActivity, binding: FragmentPetProfileBinding)
}