package com.example.moviesapp.models

import com.example.moviesapp.data.remote.responses.MovieDetailResponse
import com.example.moviesapp.data.remote.responses.MovieResponse
import com.example.moviesapp.models.detail.MovieDetailModel

const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"
fun List<MovieResponse>.toMoviesModel(): List<MovieModel> {
    return this.map { movieResponse ->
        MovieModel(
            id = movieResponse.id.toString(),
            title = movieResponse.title,
            imageUrl = "$BASE_IMAGE_URL${movieResponse.posterPath}"
        )
    }
}

fun MovieDetailResponse.toMovieDetailModel(): MovieDetailModel {
    return MovieDetailModel(
        title = this.title,
        imageUrl = this.poster_path,
        backdropPath = this.backdrop_path,
        posterPath = this.poster_path,
        budget = this.budget,
        id = this.id,
        isFavorite = false,
        overview = this.overview,
        popularity = this.popularity,
        releaseDate = this.release_date,
        revenue = this.revenue,
        status = this.status,
        tagline = this.tagline,
        video = this.video,
        voteAverage = this.vote_average,
        voteCount = this.vote_count
    )
}