package com.example.recyclerviewapi.network

import com.example.recyclerviewapi.BreedImg
import com.example.recyclerviewapi.DogsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getDogsByBreeds(@Url url:String):Response<DogsResponse>


    @GET
    suspend fun getBreedImg(@Url breed:String):Response<BreedImg>


}
