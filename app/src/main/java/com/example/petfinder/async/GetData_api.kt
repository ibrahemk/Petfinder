package com.example.petfinder.async

import com.example.petfinder.Bean.Pet
import com.example.petfinder.Coroutines.AsyncRq
import com.example.petfinder.Helper.CheckInternetConnection
import com.example.petfinder.Room.AppDatabase
import com.example.petfinder.Viewmodel.Pet_list_viewmodel

class GetData_api(val request_type:Int, val model:Pet_list_viewmodel):AsyncRq<String,Any>() {
    var check=false
    override fun onPreExecute() {
        super.onPreExecute()
        model.loading.value=true
        check=CheckInternetConnection(model.activity).haveNetworkConnection()
    }
    override fun doInBackground(vararg Param: Any): String {
        if (!check){
            return check.toString()
        }else {
            return when (request_type) {
                0 -> {
                    model.gettoken_api()
                }

                1 -> {
                    model.gettypes()
                }

                else -> {
                    model.getanimals()
                }
            }
        }
    }
    override fun onPostExecute(result: String) {
        super.onPostExecute(result)
        model.loading.value = false
        if (result.isNotEmpty()) {
            when (request_type) {
                0 -> {
                    model.parse_token(result)
                }

                1 -> {
                  model.typelist.value=model.parse_Types(result)
                    model.callapi(2)
                }

                else -> {
                    if (check) {
                       model.petlist.value= model.parse_pets(result)
                    }else{
                        model.petlist.value= AppDatabase.getAppDatabase(model.activity)!!.Pets_dao()!!.getallpet as ArrayList<Pet>?
                    }

                }
            }
        }
    }
}