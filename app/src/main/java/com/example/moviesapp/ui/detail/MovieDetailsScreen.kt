package com.example.moviesapp.ui.detail

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.example.moviesapp.data.remote.MovieDbApi
import com.example.moviesapp.models.detail.MovieDetailModel
import com.example.moviesapp.ui.detail.components.MovieDetailsHeader

@Composable
fun MovieDetailsScreen(
  onPopUp: () -> Unit,
  updateFavorites: (MovieDetailModel, Boolean) -> Unit,
  movieDetailsUiState: MovieDetailsUiState
) {
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
        IconButton(
          colors = IconButtonDefaults.iconButtonColors(
            containerColor = Color.Black.copy(alpha = 0.2f),
            contentColor = Color.White
          ),
          modifier = Modifier
            .zIndex(1f)
            .align(Alignment.TopStart)
            .padding(6.dp)
            .clip(CircleShape),
          onClick = { onPopUp() }) {
          Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
        }
        AsyncImage(
          model = MovieDbApi.IMAGE_URL_BACKDROP + movieDetailsUiState.movie.backdropPath,
          filterQuality = FilterQuality.High,
          contentDescription = "image",
          contentScale = ContentScale.Crop,
          modifier = Modifier
            .fillMaxHeight(0.7f)
            .zIndex(0f)
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
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
            .align(Alignment.BottomCenter),
        ) {
          val scrollState = rememberScrollState()
          Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
          ) {
            MovieDetailsHeader(
              movieDetailModel = movieDetailsUiState.movie,
              favoritesMovies = movieDetailsUiState.favoriteMovies,
              updateFavorites = { movie, isMovieFavorite ->
                updateFavorites(movie, isMovieFavorite)
              }
            )
            Column(
              modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .background(MaterialTheme.colorScheme.surfaceVariant)
            ) {
              Text(
                text = "Descripcion",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
              )
              Spacer(modifier = Modifier.height(8.dp))
              Text(
                text = movieDetailsUiState.movie.overview,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                  .padding(horizontal = 10.dp)
                  .padding(vertical = 10.dp)
              )
            }
          }
        }
      }
    }
  }
}
