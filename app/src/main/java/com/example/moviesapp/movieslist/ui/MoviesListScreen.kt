package com.example.moviesapp.movieslist.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.moviesapp.movieslist.ui.model.MovieModel


@Composable
fun MoviesListRoute(
    onMovieClick: () -> Unit
) {
    MoviesListScreen(
        onMovieClick = onMovieClick
    )
}

@Composable
fun MoviesListScreen(
    onMovieClick: () -> Unit
) {

    val movies = listOf(
        MovieModel("Harry Potter"),
        MovieModel("Spider man"),
        MovieModel("Shrek"),
        MovieModel("Toy Story")
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
    ) {
        items(
            movies
        ) {
            MovieCard(it) {
                onMovieClick()
            }
        }
    }
}

@Composable
@Preview
fun MoviesListScreenPreview() {
    MoviesListScreen(
        onMovieClick = {}
    )
}

