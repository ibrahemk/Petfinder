package com.example.petfinder.Interface

import androidx.fragment.app.FragmentActivity
import com.example.petfinder.Coroutines.AsyncRq
import com.example.petfinder.databinding.FragmentPetsListBinding

interface Pet_list_interface {

    fun handle_ui(binding:FragmentPetsListBinding,activity: FragmentActivity)
    fun handle_loading(show:Boolean)
    fun set_adapter(type:Int)
    fun callapi(type: Int)
    fun handle_scroll()
    fun fireapi(async: AsyncRq<String,Any>)
    fun cancelapi(async: AsyncRq<String,Any>?)

}