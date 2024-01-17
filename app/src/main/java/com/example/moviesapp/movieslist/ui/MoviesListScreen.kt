package com.example.moviesapp.movieslist.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MoviesListRoute(
    onMovieClick: () -> Unit,
    viewModel: MoviesListViewModel = viewModel(
        factory = MoviesListViewModel.Factory
    )
) {
    val moviesUiState by viewModel.moviesListUiState.collectAsState()

    MoviesListScreen(
        moviesUiState = moviesUiState,
        onMovieClick = onMovieClick
    )
}

@Composable
fun MoviesListScreen(
    moviesUiState: MoviesUiState,
    onMovieClick: () -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
    ) {
        items(moviesUiState.moviesList) {
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
        MoviesUiState(),
        onMovieClick = {}
    )
}

