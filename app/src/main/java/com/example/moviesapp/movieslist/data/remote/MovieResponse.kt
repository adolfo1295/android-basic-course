package com.example.moviesapp.movieslist.data.remote

import com.squareup.moshi.Json

data class MovieResultResponse (
    @Json(name = "page") val page : Int,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "results") val results : List<MovieResponse>
)

data class MovieResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "vote_average") val voteAverage: Float,
    @Json(name = "overview") val overview: String,
    @Json(name = "poster_path") val posterPath: String,
)