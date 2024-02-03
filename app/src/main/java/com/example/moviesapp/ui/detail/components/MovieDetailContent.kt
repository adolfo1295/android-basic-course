package com.example.moviesapp.ui.detail.components

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviesapp.data.local.entities.MovieEntity
import com.example.moviesapp.data.remote.MovieDbApi
import com.example.moviesapp.models.detail.MovieDetailModel

@Composable
fun MovieDetailContent(
  favoriteMovies: List<MovieEntity>,
  movieDetailModel: MovieDetailModel,
  onPopUp: () -> Unit,
  updateFavorites: (movieDetailModel: MovieDetailModel, isMovieInFavorites: Boolean) -> Unit
) {
  val isMovieFavorites =
    (favoriteMovies.find { it.id == movieDetailModel.id }
      ?.let { true } == true)

  Box(
    modifier = Modifier
      .fillMaxSize(),
    contentAlignment = Alignment.TopCenter
  ) {

    var imageBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var containerIconColor by remember { mutableStateOf<Int?>(null) }

    AsyncImage(
      model = ImageRequest.Builder(LocalContext.current)
        .data(MovieDbApi.IMAGE_URL_BACKDROP + movieDetailModel.backdropPath)
        .crossfade(true)
        .allowHardware(false)
        .build(),
      filterQuality = FilterQuality.High,
      contentDescription = "image",
      contentScale = ContentScale.Crop,
      onSuccess = { success ->
        imageBitmap = success.result.drawable.toBitmap()
        imageBitmap?.let {
          val palette = Palette.from(it).generate()
          containerIconColor = palette.getMutedColor(Color.Unspecified.toArgb())
        }
      },
      modifier = Modifier
        .fillMaxHeight(0.8f)
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

    IconButton(
      modifier = Modifier
        .align(Alignment.TopEnd)
        .padding(6.dp)
        .clip(CircleShape)
        .zIndex(1f),
      colors = IconButtonDefaults.iconButtonColors(
        containerColor = containerIconColor?.let { Color(it) }
          ?: MaterialTheme.colorScheme.primaryContainer,
        contentColor = Color.White
      ),
      onClick = {
        updateFavorites(movieDetailModel, isMovieFavorites)
      }) {
      Icon(
        imageVector = if (isMovieFavorites) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
        contentDescription = "fav"
      )
    }

  }
}