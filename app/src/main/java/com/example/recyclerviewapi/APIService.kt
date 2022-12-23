package com.example.recyclerviewapi

import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    fun getDogsByBreeds(@Url url:String):retrofit2.Response<DogsResponse>


}