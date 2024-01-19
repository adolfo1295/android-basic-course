package com.example.moviesapp.movieslist.ui.model

import com.example.moviesapp.data.remote.MovieResponse

const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"
fun List<MovieResponse>.toMoviesModel() : List<MovieModel> {
    return this.map { movieResponse ->
        MovieModel(
            title = movieResponse.title,
            imageUrl = "$BASE_IMAGE_URL${movieResponse.posterPath}"
        )
    }
}