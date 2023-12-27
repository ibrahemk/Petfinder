package com.example.petfinder.async

import com.example.petfinder.Coroutines.AsyncRq
import com.example.petfinder.Room.AppDatabase
import com.example.petfinder.Viewmodel.Pet_list_viewmodel

class Add_listot_room_async(val model:Pet_list_viewmodel):AsyncRq<String,Any>() {
    override fun doInBackground(vararg Param: Any): String {
        AppDatabase.getAppDatabase(model.activity)!!.Pets_dao()!!.deletePets()
        AppDatabase.getAppDatabase(model.activity)!!.Pets_dao()!!.insertAll(model.petlist.value)
        AppDatabase.destroyInstance()
        return ""
    }

    override fun onPostExecute(result: String) {
        super.onPostExecute(result)
    }
}