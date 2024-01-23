package com.example.moviesapp.moviedetails.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.moviesapp.data.remote.MovieDbApi
import com.example.moviesapp.moviedetails.ui.components.MovieDetailsHeader

@Composable
fun MovieDetailsRoute(
  movieId: String,
  onPopUp: () -> Unit,
) {
  val viewModel: MovieDetailsViewModel = viewModel(
    factory = MovieDetailsViewModel.Factory
  )
  Log.d("fofito", "MovieDetailsRoute: ")
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

  Log.d("fofimac", "MovieDetailsScreen: ${movieDetailsUiState.isLoading}")

  if (movieDetailsUiState.isLoading) {
    Box(
      modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.surface)
    ) {
      CircularProgressIndicator(
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.align(Alignment.Center)
      )
    }
  } else {
    movieDetailsUiState.movie?.let {
      Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter,
      ) {
        AsyncImage(
          model = MovieDbApi.IMAGE_URL + movieDetailsUiState.movie.backdropPath,
          contentDescription = "image",
          contentScale = ContentScale.Crop,
          modifier = Modifier
            .fillMaxHeight(0.7f)
            .drawWithCache {
              onDrawWithContent {
                drawContent()
                drawRect(
                  Brush.verticalGradient(
                    0.5f to Color.White.copy(alpha = 0F),
                    1F to Color.White
                  )
                )
              }
            },
        )
        Box(
          modifier = Modifier
            .padding(horizontal = 10.dp)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
            .align(Alignment.BottomCenter),
        ) {
          Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
          ) {
            MovieDetailsHeader(movieDetailModel = movieDetailsUiState.movie)
            Spacer(modifier = Modifier.height(10.dp))
            Text(
              text = "Descripcion",
              style = MaterialTheme.typography.titleLarge,
              color = MaterialTheme.colorScheme.onSurfaceVariant,
              fontWeight = FontWeight.Bold,
              modifier = Modifier.padding(horizontal = 10.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
              text = movieDetailsUiState.movie.overview,
              style = MaterialTheme.typography.bodyMedium,
              color = MaterialTheme.colorScheme.onSurfaceVariant,
              modifier = Modifier.padding(horizontal = 10.dp)
            )
          }
        }
      }
    }
  }
}
