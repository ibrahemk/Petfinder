package com.example.petfinder.Retrofitapis

import java.net.URL


class RF_Requests {

    fun Get_request(url:String): String {
        var output:String=""
        val apiInterface = APIClient.getClient(extractUrl(url))!!.create(
            APIInterface::class.java
        )
        output= apiInterface.Getrequest(url)?.execute()?.body().toString()




        return output
    }


    fun extractUrl(host: String):String {
        val url = URL(host)
        val baseUrl: String = url.protocol.toString() + "://" + url.host
        return baseUrl
    }






}