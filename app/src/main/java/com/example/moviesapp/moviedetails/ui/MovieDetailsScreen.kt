package com.example.moviesapp.moviedetails.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun MovieDetailsRoute() {
  MovieDetailsScreen()
}

@Composable
fun MovieDetailsScreen() {
  Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.CenterStart,
  ) {
    Column(
      modifier = Modifier.fillMaxSize(),
      verticalArrangement = Arrangement.Top,
    ) {
      AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
          .data("https://cdn.vox-cdn.com/thumbor/Gi91EGDL8Szz67xGWFO0jTGy1ec=/0x0:1920x1080/1200x800/filters:focal(755x89:1061x395)/cdn.vox-cdn.com/uploads/chorus_image/image/72144783/harrypotter.0.jpg")
          .crossfade(true)
          .build(),
        contentDescription = "image",
        contentScale = ContentScale.Crop,
      )
      Spacer(modifier = Modifier.height(8.dp))
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = "Harry Potter",
          style = MaterialTheme.typography.titleLarge,
          textAlign = TextAlign.Start,
          fontSize = 30.sp
        )
        IconButton(
          onClick = { },

          ) {
          Icon(
            imageVector = Icons.Rounded.Favorite,
            contentDescription = "",
            modifier = Modifier.size(40.dp)
          )
        }
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsScreenPreview() {
  MovieDetailsScreen()
}