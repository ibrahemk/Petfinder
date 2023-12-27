package com.example.petfinder.Bean

import android.content.Context
import androidx.fragment.app.FragmentActivity
import com.example.petfinder.MainActivity

object Global {
    val client_id="1ZOpFwMEOdUC5hbmwfGeui7HakB3CJLqaBPznZEhqLjdFFyP6H"
    val client_secret="QOx2RM90ErDWRt23YYdnwYj3kPeVJuSAvPtO4pYT"
    var token=""
    var gettoken="https://api.petfinder.com/v2/oauth2/token"
    var gettypes="https://api.petfinder.com/v2/types"
    var getallanimals="https://api.petfinder.com/v2/animals"
    fun setToken(fragmentActivity: FragmentActivity, token: String) {
        this.token=token
        val prefs = fragmentActivity.getSharedPreferences(
            MainActivity::class.java.simpleName,
            Context.MODE_PRIVATE
        )
        val editor = prefs.edit()
        editor.remove("Token")
        editor.putString("Token", token)
        editor.apply()
    }
    fun getToken(fragmentActivity: FragmentActivity): String? {
        val prefs = fragmentActivity.getSharedPreferences(
            MainActivity::class.java.simpleName,
            Context.MODE_PRIVATE
        )
        return prefs.getString("Token", "")
    }
}