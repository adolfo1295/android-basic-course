package com.example.moviesapp.movieslist.ui

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moviesapp.movieslist.ui.model.MovieModel

@Composable
fun MoviesListRoute(
    onMovieClick: () -> Unit,
    viewModel: MoviesListViewModel = viewModel(
        factory = MoviesListViewModel.Factory
    )
) {

    val moviesUiState by viewModel.moviesListUiState.collectAsStateWithLifecycle()

    MoviesListScreen(
        moviesUiState = moviesUiState,
        onMovieClick = onMovieClick,
        onFavoriteClick = viewModel::onFavoriteMovieClick
    )
}

@Composable
fun MoviesListScreen(
    moviesUiState: MoviesUiState,
    onMovieClick: () -> Unit,
    onFavoriteClick: (movie: MovieModel) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
    ) {
        items(moviesUiState.moviesList) {
            MovieCard(
                movie = it,
                onMovieClick = onMovieClick,
                onFavoriteClick = onFavoriteClick
            )
        }
    }
}

@Composable
@Preview
fun MoviesListScreenPreview() {
    MoviesListScreen(
        MoviesUiState(),
        onMovieClick = {},
        onFavoriteClick = {}
    )
}

