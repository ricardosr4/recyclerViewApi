package com.example.recyclerviewapi.data.network

import com.example.recyclerviewapi.data.model.BreedImgResponse
import com.example.recyclerviewapi.data.model.DogsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getDogsByBreeds(@Url url:String):Response<DogsResponse>


    @GET
    suspend fun getBreedImg(@Url breed:String):Response<BreedImgResponse>


}
