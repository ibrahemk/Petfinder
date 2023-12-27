package com.example.petfinder.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import com.example.petfinder.Bean.Global
import com.example.petfinder.R
import com.example.petfinder.Viewmodel.Pet_list_viewmodel

import com.example.petfinder.databinding.FragmentPetsListBinding


class Pets_list_Fragment : Fragment() {

lateinit var binding:FragmentPetsListBinding
    val model by viewModels<Pet_list_viewmodel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding= FragmentPetsListBinding.inflate(inflater, container, false)
        model.handle_ui(binding,requireActivity())
        model.changeToolbarTitle(
            "Pets",
            binding.toolbarLayout
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.token.observe(viewLifecycleOwner){
            model.callapi(0)
            model.callapi(1)
        }
        model.loading.observe(viewLifecycleOwner){
            model.handle_loading(it)
        }
        model.typelist.observe(viewLifecycleOwner){
            model.set_adapter(0)
        }
        model.petlist.observe(viewLifecycleOwner){
            model.set_adapter(1)
        }


        if (Global.token.isNotEmpty()){
            model.callapi(1)
        }else {
            model.callapi(0)
        }
    }
    companion object {

        @JvmStatic
        fun newInstance() =
            Pets_list_Fragment().apply {}
    }

    override fun onDetach() {
        super.onDetach()
        model.cancelapi(model.get_Token)
        model.cancelapi(model.gettypes)
        model.cancelapi(model.get_Pets)

    }
}