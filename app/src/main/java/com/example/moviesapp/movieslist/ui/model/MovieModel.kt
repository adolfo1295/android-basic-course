package com.example.moviesapp.movieslist.ui.model

data class MovieModel(
    val title: String,
    val imageUrl: String,
    var isFavorite: Boolean = false,
)