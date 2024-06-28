package com.example.mymovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovies.model.MovieTable
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    private val repository = MovieRepository()
    private val _movies = MutableLiveData<List<MovieTable>>()
    val movies: LiveData<List<MovieTable>> get() = _movies


    private val _upcomingMovies = MutableLiveData<List<MovieTable>>()
    val upcomingMovies:LiveData<List<MovieTable>> get() = _upcomingMovies

    init {
        fetchMovies()
        fetchUpcomingMovies()
    }

    private fun fetchMovies(){
        viewModelScope.launch {
            try {
                val movieList = repository.getMovies()
                _movies.value = movieList
            } catch (e: Exception){

            }

        }
    }

    private fun fetchUpcomingMovies(){
        viewModelScope.launch {
            try {
                val upcomingMovieList = repository.getUpcomingMovies()
                _upcomingMovies.value = upcomingMovieList
            } catch (e: Exception){}
        }
    }
}