package com.example.moviesapp.ui.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.example.moviesapp.data.remote.MovieDbApi
import com.example.moviesapp.models.detail.MovieDetailModel
import com.example.moviesapp.ui.detail.components.MovieDetailContent
import com.example.moviesapp.ui.detail.components.MovieDetailSheetContent
import com.example.moviesapp.ui.detail.components.TextWithStarsCount

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
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

      val scaffoldState = rememberBottomSheetScaffoldState()

      BottomSheetScaffold(
        scaffoldState = scaffoldState,
        // This is the height in collapsed state
        sheetPeekHeight = (LocalConfiguration.current.screenHeightDp * 0.4).dp,
        sheetContent = {
          MovieDetailSheetContent(movieDetailModel = it)
        }
      ) { innerPadding ->
        MovieDetailContent(
          favoriteMovies = movieDetailsUiState.favoriteMovies,
          movieDetailModel = it,
          onPopUp = onPopUp,
          updateFavorites = { movieDetailModel, isMovieInFavorites ->
            updateFavorites(movieDetailModel, isMovieInFavorites)
          }
        )
      }
    }
  }
}