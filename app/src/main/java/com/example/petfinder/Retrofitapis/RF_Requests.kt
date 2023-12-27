package com.example.petfinder.Retrofitapis

import android.content.Context
import android.provider.Settings.Global
import android.util.Log
import androidx.fragment.app.FragmentActivity
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONException
import org.json.JSONObject
import java.net.URL
import kotlin.math.log


class RF_Requests {

    fun Get_request(url:String): String {
        var output:String=""
        val apiInterface = APIClient.getClient(extractUrl(url))!!.create(
            APIInterface::class.java
        )
        Log.e("Token", com.example.petfinder.Bean.Global.token);
        val executed =
            apiInterface.Getrequest(com.example.petfinder.Bean.Global.token,url)?.execute()

        output = if (executed!!.isSuccessful){
            executed.body().toString()
        }else{

            executed.raw().toString()
        }


        return output
    }
    fun Post_json(url: String, param: JSONObject, activity: FragmentActivity):String{
        var output:String=""


        val apiInterface = APIClient.getClient(extractUrl(url))!!.create(
            APIInterface::class.java
        )


        val executed =
            apiInterface.POStrequestjs(url,
                createPartFromStringjson(param)!!
            )?.execute()


        output = if (executed!!.isSuccessful){
            executed.body().toString()
        }else{

            executed.raw().toString()
        }



        return output

    }

    fun Post(url: String, param: HashMap<String,Any>, activity: Context):String{
        var output:String=""


        val apiInterface = APIClient.getClient(extractUrl(url))!!.create(
            APIInterface::class.java
        )
        val executed =
            apiInterface.POStrequest(url,param)?.execute()


        output = if (executed!!.isSuccessful){
            executed.body().toString()
        }else{

            "${executed.message()} ${executed.errorBody().toString()}"
        }



        return output

    }
    fun createPartFromStringjson(param:JSONObject): RequestBody? {
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), param.toString())
    }
    fun extractUrl(host: String):String {
        val url = URL(host)
        val baseUrl: String = url.protocol.toString() + "://" + url.host
        return baseUrl
    }






}