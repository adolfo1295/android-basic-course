package com.example.moviesapp.ui.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.moviesapp.data.remote.MovieDbApi
import com.example.moviesapp.models.detail.MovieDetailModel

@Composable
fun MovieDetailSheetContent(
  movieDetailModel: MovieDetailModel
) {
  Column(
    Modifier
      .fillMaxWidth()
      .heightIn(
        min = 100.dp,
        max = (LocalConfiguration.current.screenHeightDp * 0.8).dp
      )
      .padding(horizontal = 10.dp, vertical = 5.dp)
  ) {
    AsyncImage(
      modifier = Modifier
        .height(100.dp)
        .width(100.dp)
        .align(Alignment.CenterHorizontally)
        .padding(vertical = 10.dp, horizontal = 10.dp)
        .clip(RoundedCornerShape(20.dp)),
      filterQuality = FilterQuality.High,
      model = MovieDbApi.IMAGE_URL_THUMBNAIL + movieDetailModel.posterPath,
      contentDescription = "image",
      contentScale = ContentScale.Crop,
    )
    Spacer(modifier = Modifier.height(5.dp))
    Text(
      text = movieDetailModel.title,
      style = MaterialTheme.typography.titleLarge,
      color = MaterialTheme.colorScheme.onSurfaceVariant,
      fontSize = 30.sp,
      fontWeight = FontWeight.Bold,
      textAlign = TextAlign.Center,
      modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(2.dp))
    TextWithStarsCount(voteCount = movieDetailModel.voteAverage.toString())
    Spacer(modifier = Modifier.height(10.dp))
    Text(
      text = movieDetailModel.overview,
      style = MaterialTheme.typography.bodyLarge,
      fontSize = 18.sp,
      modifier = Modifier.padding(horizontal = 6.dp, vertical = 10.dp)
    )
    Spacer(modifier = Modifier.height(10.dp))
  }
}