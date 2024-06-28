package com.example.mymovies

import com.example.mymovies.model.MovieTable
import com.example.mymovies.model.RetrofitInstance

class MovieRepository {
    private val api = RetrofitInstance.api

    suspend fun getMovies(): List<MovieTable>{
        return api.getAllMovies()
    }

    suspend fun getUpcomingMovies():List<MovieTable> {
        return api.getUpcomingMovies()
    }
}