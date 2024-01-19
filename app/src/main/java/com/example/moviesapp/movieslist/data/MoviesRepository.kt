package com.example.moviesapp.movieslist.data

import com.example.moviesapp.data.remote.MovieDbApi
import com.example.moviesapp.data.remote.MovieResponse

class MoviesRepository(
    private val movieDbApi: MovieDbApi
) {
   suspend fun getMovies() : List<MovieResponse> {
       return movieDbApi.getMovies().results
   }
}