package com.example.petfinder.Interface

import androidx.fragment.app.FragmentActivity
import com.example.petfinder.Bean.Pet
import com.example.petfinder.Bean.Pet_type
import com.example.petfinder.Coroutines.AsyncRq
import com.example.petfinder.databinding.FragmentPetsListBinding

interface Pet_list_interface {

    fun handle_ui(binding:FragmentPetsListBinding,activity: FragmentActivity)
    fun handle_loading(show:Boolean)
    fun set_adapter(type:Int)
    fun callapi(type: Int)
    fun handle_scroll()
    fun fireapi(type: Int)
    fun cancelapi(async: AsyncRq<String,Any>?)
fun gettoken_api():String
fun gettypes():String
fun getanimals():String
fun parse_token(result:String)
fun parse_Types(result:String):ArrayList<Pet_type>
    fun parse_pets(result:String):ArrayList<Pet>
fun Checkerror(result:String):Boolean
}