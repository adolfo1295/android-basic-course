package com.example.moviesapp.moviedetails.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
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
import coil.compose.AsyncImage
import com.example.moviesapp.data.remote.MovieDbApi
import com.example.moviesapp.models.detail.MovieDetailModel

@Composable
fun MovieDetailsHeader(movieDetailModel: MovieDetailModel) {
  Row(
    modifier = Modifier.fillMaxWidth()
  ) {
    AsyncImage(
      modifier = Modifier
        .offset(x = -(10.dp), y = -(20.dp))
        .size(100.dp)
        .clip(CircleShape),
      filterQuality = FilterQuality.High,
      model = MovieDbApi.IMAGE_URL + movieDetailModel.posterPath,
      contentDescription = "image",
      contentScale = ContentScale.Crop,
    )
    Spacer(modifier = Modifier.width(5.dp))
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
        .offset(x = -(5.dp), y = -(20.dp))
        .clip(CircleShape),
      colors = IconButtonDefaults.iconButtonColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
      ),
      onClick = { }) {
      Icon(imageVector = Icons.Filled.Favorite, contentDescription = "fav")
    }
  }
}