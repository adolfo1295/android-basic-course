package com.example.moviesapp.movieslist.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.moviesapp.data.remote.MovieDbApi
import com.example.moviesapp.models.detail.MovieDetailModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieCard(
  movie: MovieDetailModel,
  onMovieClick: (Int) -> Unit,
  onFavoriteClick: (movie: MovieDetailModel, isMovieInFavorites: Boolean) -> Unit,
  isMovieFavorite: Boolean
) {
  Card(
    elevation = CardDefaults.cardElevation(2.dp),
    onClick = {
      onMovieClick(movie.id)
    }
  ) {
    Box(
      modifier = Modifier.fillMaxSize()
    ) {
      Column(
        modifier = Modifier.fillMaxSize()
      ) {
        SubcomposeAsyncImage(
          model = ImageRequest.Builder(LocalContext.current)
            .data(MovieDbApi.IMAGE_URL_HOME + movie.imageUrl)
            .crossfade(true)
            .build(),
          loading = {
            CircularProgressIndicator()
          },
          contentScale = ContentScale.Crop,
          contentDescription = null,
          modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
        )
      }

      Icon(
        imageVector = if (isMovieFavorite) Icons.Filled.Favorite
        else Icons.Default.FavoriteBorder,
        contentDescription = "No favorite items icon",
        modifier = Modifier
          .size(50.dp)
          .padding(8.dp)
          .align(Alignment.TopEnd)
          .clickable {
            onFavoriteClick(movie, isMovieFavorite)
          }
      )
    }

  }
}