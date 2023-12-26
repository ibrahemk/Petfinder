package com.example.petfinder.fragment

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.petfinder.Bean.Pet

import com.example.petfinder.Viewmodel.Pet_profile_viewmodel
import com.example.petfinder.databinding.FragmentPetProfileBinding



class Pet_profile_Fragment : Fragment() {
  lateinit var binding:FragmentPetProfileBinding
    val model by viewModels<Pet_profile_viewmodel>()
    lateinit var pet:Pet
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding= FragmentPetProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

                model.handleui(pet,requireActivity(),binding)


    }

    companion object {

        @JvmStatic
        fun newInstance(pet: Pet) =
            Pet_profile_Fragment().apply {
                this.pet=pet
            }
    }
}