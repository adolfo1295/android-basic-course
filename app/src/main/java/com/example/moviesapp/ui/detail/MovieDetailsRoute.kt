package com.example.moviesapp.ui.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MovieDetailsRoute(
  movieId: String,
  onPopUp: () -> Unit,
) {
  val movieDetailViewModel: MovieDetailsViewModel = viewModel(
    factory = MovieDetailsViewModel.Factory
  )
  movieDetailViewModel.getMovieDetails(movieId = movieId)
  val movieDetailsUiState by movieDetailViewModel.movieDetailsUiState.collectAsStateWithLifecycle()

  MovieDetailsScreen(
    movieDetailsUiState = movieDetailsUiState,
    onPopUp = onPopUp,
    updateFavorites = { movieDetailModel, isMovieFavorite ->
      movieDetailViewModel.updateFavorites(
        movieDetailModel = movieDetailModel,
        isMovieFavorite = isMovieFavorite
      )
    }
  )
}
