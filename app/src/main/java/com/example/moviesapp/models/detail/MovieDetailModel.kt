package com.example.moviesapp.models.detail

data class MovieDetailModel(
    val title: String,
    val imageUrl: String,
    var isFavorite: Boolean = false,
    val backdropPath: String,
    val budget: Int,
    val id: Int,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val revenue: Int,
    val status: String,
    val tagline: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)