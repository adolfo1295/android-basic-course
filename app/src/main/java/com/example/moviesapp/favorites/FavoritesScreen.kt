package com.example.moviesapp.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moviesapp.movieslist.ui.MovieCard


@Composable
fun FavoritesRoute() {
  val movieDetailViewModel: FavoriteMoviesViewModel = viewModel(
    factory = FavoriteMoviesViewModel.Factory
  )

  val favoriteMoviesUiState by movieDetailViewModel.favoriteMoviesUiState.collectAsStateWithLifecycle()

  FavoritesScreen(
    favoriteMoviesUiState = favoriteMoviesUiState,
    onMovieClick = { movieId ->

    }
  )


}

@Composable
fun FavoritesScreen(
  favoriteMoviesUiState: FavoriteMoviesUiState,
  onMovieClick: (Int) -> Unit
) {
  if (favoriteMoviesUiState.favoriteMovies.isNotEmpty()) {
    LazyVerticalGrid(
      columns = GridCells.Fixed(2),
      modifier = Modifier.fillMaxSize(),
    ) {
      items(favoriteMoviesUiState.favoriteMovies) { movie ->
        MovieCard(
          movie,
          onMovieClick = { movieId ->
            onMovieClick(movieId)
          },
          onFavoriteClick = { movie, isMovieInFavorites ->
          },
          isMovieFavorite = false
        )
      }
    }
  } else {
    Column(
      modifier = Modifier.fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      Icon(
        imageVector = Icons.Default.FavoriteBorder,
        tint = Color.Gray,
        contentDescription = "No favorite items icon",
        modifier = Modifier
          .fillMaxWidth()
          .size(300.dp)
          .padding(8.dp)
      )
      Text(
        text = "Sin favoritos",
        textAlign = TextAlign.Center,
        fontSize = 24.sp,
        modifier = Modifier
          .fillMaxWidth()
      )
    }
  }
}