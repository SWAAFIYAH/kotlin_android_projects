package com.example.mymovies.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MovieApiService {
    @GET("movies/movies")
    suspend fun getAllMovies(): List<MovieTable>

    @GET("movies/upcoming")
    suspend fun getUpcomingMovies(): List<MovieTable>
}

object RetrofitInstance{
    val base_url ="https://api.themoviedb.org/3/"
    val api: MovieApiService by lazy {

       Retrofit.Builder()
           .baseUrl(base_url)
           .addConverterFactory(GsonConverterFactory.create())
           .build()
           .create(MovieApiService::class.java)
    }
}