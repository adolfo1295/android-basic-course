package com.example.moviesapp.moviedetails.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviesapp.data.remote.MovieDbApi

@Composable
fun MovieDetailsRoute(
  movieId: String,
  onPopUp: () -> Unit,
  viewModel: MovieDetailsViewModel = viewModel(
    factory = MovieDetailsViewModel.Factory
  )
) {
    viewModel.getMovieDetails(movieId = movieId)
  val movieDetailsUiState by viewModel.movieDetailsUiState.collectAsStateWithLifecycle()
  MovieDetailsScreen(
    movieDetailsUiState = movieDetailsUiState,
    onPopUp = onPopUp
  )
}

@Composable
fun MovieDetailsScreen(
  onPopUp: () -> Unit,
  movieDetailsUiState: MovieDetailsUiState
) {
  Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.TopCenter,
  ) {
    AsyncImage(
      modifier = Modifier.fillMaxHeight(0.6f),
      model = ImageRequest.Builder(LocalContext.current)
        .data(MovieDbApi.IMAGE_URL + movieDetailsUiState.movie?.backdropPath.orEmpty())
        .crossfade(true)
        .build(),
      contentDescription = "image",
      contentScale = ContentScale.Crop,
    )
  }
}

//https://dribbble.com/shots/4432184-Daily-UI-7-7-Movie-App-UI/attachments/10389850?mode=media
