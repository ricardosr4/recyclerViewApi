package com.example.recyclerviewapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recyclerviewapi.databinding.ActivityMainBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var Binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breeds/")
        .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}