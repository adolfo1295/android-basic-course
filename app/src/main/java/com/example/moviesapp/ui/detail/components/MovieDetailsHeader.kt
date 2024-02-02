package com.example.moviesapp.ui.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.example.moviesapp.data.local.entities.MovieEntity
import com.example.moviesapp.data.remote.MovieDbApi
import com.example.moviesapp.models.detail.MovieDetailModel

@Composable
fun MovieDetailsHeader(
  movieDetailModel: MovieDetailModel,
  favoritesMovies: List<MovieEntity>,
  updateFavorites: (MovieDetailModel, Boolean) -> Unit
) {

  val isMovieFavorites = (favoritesMovies.find { it.id == movieDetailModel.id }
    ?.let { true } == true)

  Box(
    modifier = Modifier
      .fillMaxWidth()
      .wrapContentHeight()
  ) {
    IconButton(
      modifier = Modifier
        .align(Alignment.TopEnd)
        .offset(x = -(5.dp), y = -(20.dp))
        .clip(CircleShape)
        .zIndex(1f),
      colors = IconButtonDefaults.iconButtonColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
      ),
      onClick = {
        updateFavorites(movieDetailModel, isMovieFavorites)
      }) {
      Icon(
        imageVector = if (isMovieFavorites) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
        contentDescription = "fav"
      )
    }

    Row(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
        .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
      AsyncImage(
        modifier = Modifier
          .height(100.dp)
          .width(100.dp)
          .padding(vertical = 10.dp, horizontal = 10.dp)
          .clip(RoundedCornerShape(20.dp)),
        filterQuality = FilterQuality.High,
        model = MovieDbApi.IMAGE_URL_THUMBNAIL + movieDetailModel.posterPath,
        contentDescription = "image",
        contentScale = ContentScale.Crop,
      )
      Spacer(modifier = Modifier.width(10.dp))
      Column(
        modifier = Modifier
          .padding(top = 5.dp)
          .fillMaxWidth(0.7f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
      ) {
        Text(
          text = movieDetailModel.title,
          style = MaterialTheme.typography.titleLarge,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          fontSize = 20.sp,
          fontWeight = FontWeight.Bold,
        )
        TextWithStarsCount(voteCount = movieDetailModel.voteAverage.toString())
      }
    }

  }

  /*  Row(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
    ) {
      AsyncImage(
        modifier = Modifier
          .height(100.dp)
          .width(100.dp)
          .padding(vertical = 10.dp, horizontal = 10.dp)
          .clip(RoundedCornerShape(20.dp)),
        filterQuality = FilterQuality.High,
        model = MovieDbApi.IMAGE_URL_THUMBNAIL + movieDetailModel.posterPath,
        contentDescription = "image",
        contentScale = ContentScale.Crop,
      )
      Spacer(modifier = Modifier.width(10.dp))
      Column(
        modifier = Modifier
          .padding(top = 5.dp)
          .weight(1f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
      ) {
        Text(
          text = movieDetailModel.title,
          style = MaterialTheme.typography.titleLarge,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          fontSize = 20.sp,
          fontWeight = FontWeight.Bold,
        )
        TextWithStarsCount(voteCount = movieDetailModel.voteAverage.toString())
      }
      IconButton(
        modifier = Modifier
          .offset(x = -(25.dp), y = -(20.dp))
          .clip(CircleShape),
        colors = IconButtonDefaults.iconButtonColors(
          containerColor = MaterialTheme.colorScheme.primaryContainer,
          contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        onClick = {
          updateFavorites(movieDetailModel, isMovieFavorites)
        }) {
        Icon(
          imageVector = if (isMovieFavorites) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
          contentDescription = "fav"
        )
      }
    }*/

}