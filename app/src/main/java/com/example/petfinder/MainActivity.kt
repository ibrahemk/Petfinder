package com.example.petfinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.petfinder.Bean.Global
import com.example.petfinder.fragment.Pets_list_Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()
        if (!Global.getToken(this).isNullOrEmpty()){
            Global.token= Global.getToken(this).toString()
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, Pets_list_Fragment.newInstance(), "home").commit()
    }
}