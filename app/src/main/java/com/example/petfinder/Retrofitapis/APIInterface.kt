package com.example.petfinder.Retrofitapis


import retrofit2.Call
import retrofit2.http.*



interface APIInterface {

    @GET
    fun Getrequest(@Url url:String,): Call<String?>?




}