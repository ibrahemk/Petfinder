package com.example.petfinder.Retrofitapis


import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*



interface APIInterface {

    @GET
    fun Getrequest(@Header("Authorization") Key_Sec:String,@Url url:String,): Call<String?>?

    @POST
    fun POStrequestjs( @Url url:String, @Body requestBody: RequestBody): Call<String?>?

    @FormUrlEncoded
    @POST
    fun POStrequest( @Url url:String,@FieldMap  requestBody: HashMap<String, Any> ): Call<String?>?


}