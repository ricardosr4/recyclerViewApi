package com.example.recyclerviewapi.ui.activity

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewapi.data.network.APIService
import com.example.recyclerviewapi.ui.adapters.DogAdapter
import com.example.recyclerviewapi.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: DogAdapter
    private val dogList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        getBreedsList()





    }

    private fun initRecyclerView() {
        adapter = DogAdapter(dogList)
        binding.rvDogs.layoutManager = LinearLayoutManager(this)
        binding.rvDogs.adapter = adapter

    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breeds/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getBreedsList() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getDogsByBreeds("list")
            val puppies = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    val message: List<String> = puppies?.message ?: emptyList()
                    dogList.clear()
                    dogList.addAll(message)
                    adapter.notifyDataSetChanged()
                } else {
                    showError()
                }
                hideKeyboard()


            }


        }
    }

    //ocultar teclado despues de escribir (hideKeyboard)
    private fun hideKeyboard() {

        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.viewRoot.windowToken, 0)

    }

    private fun showError() {
        Toast.makeText(this, "ha ocurrido un error", Toast.LENGTH_SHORT).show()


    }


}