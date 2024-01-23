package com.example.moviesapp.data.repositories

import com.example.moviesapp.data.remote.MovieDbApi
import com.example.moviesapp.data.remote.responses.MovieDetailResponse
import com.example.moviesapp.data.remote.responses.MovieResponse

class MoviesRepository(
    private val movieDbApi: MovieDbApi
) {
    suspend fun getMovies(): List<MovieResponse> {
        return movieDbApi.getMovies().results
    }

    suspend fun getMovieDetail(movieId: String): MovieDetailResponse {
        return movieDbApi.getMovieDetail(movieId = movieId)
    }
}