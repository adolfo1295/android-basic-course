package com.example.moviesapp.models

data class MovieModel(
    val id: String,
    val title: String,
    val imageUrl: String,
    var isFavorite: Boolean = false,
)