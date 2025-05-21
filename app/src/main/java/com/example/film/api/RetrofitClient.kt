package com.example.film.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    fun createServices():FilmServices{
        val retrofit=Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/").
        addConverterFactory(GsonConverterFactory.create()).build()
        return retrofit.create(FilmServices::class.java)
    }

}