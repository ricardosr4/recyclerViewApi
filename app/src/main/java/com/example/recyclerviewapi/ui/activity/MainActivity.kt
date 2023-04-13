package com.example.recyclerviewapi.ui.activity

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewapi.R
import com.example.recyclerviewapi.data.network.APIService
import com.example.recyclerviewapi.ui.adapters.DogAdapter
import com.example.recyclerviewapi.databinding.ActivityMainBinding
import com.example.recyclerviewapi.databinding.FragmentDialogImgBreedBinding
import com.example.recyclerviewapi.ui.fragments.dialog.DialogImgBreedFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

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
        adapter = DogAdapter(dogList){dogList,viewId: Int -> breedsClicked(dogList, viewId) }
        binding.rvDogs.layoutManager = LinearLayoutManager(this)
        binding.rvDogs.adapter = adapter
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //todo esta funcion sirve para llamar a la lista de razas
    private fun getBreedsList() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getDogsByBreeds("breeds/list")
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
    //todo esta funcion sirve para llamar a la imagen de una raza
    private fun getImageBreed(query:String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getBreedImg("breed/$query/images/random")
            val imgbreed = call.body()
            runOnUiThread {
                if(call.isSuccessful){
                    val img :String = imgbreed?.message ?: ""
                    // todo se llama al dialog fragment enviando la imagen que se recupera desde la api
                    showDialog(img)
                }else {
                    showError()
                }
            }
        }
    }

    private fun showDialog(img:String) {
        val dialog = DialogImgBreedFragment.newInstance(img)
        dialog.show(supportFragmentManager,"showImageBreed")
    }
    // todo  metodo para llamar a la imagen de la raza seleccionada

    //todo ocultar teclado despues de escribir (hideKeyboard)
    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.viewRoot.windowToken, 0)
    }

    private fun showError() {
        Toast.makeText(this, "ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }


    private fun breedsClicked(breed: String, viewId: Int) {
        when (viewId) {
            R.id.tvDogs -> { getImageBreed(breed) }
            R.id.cardbreed -> { getImageBreed(breed) }
        }
    }

}