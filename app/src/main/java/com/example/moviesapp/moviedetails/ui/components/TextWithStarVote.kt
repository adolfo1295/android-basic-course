package com.example.moviesapp.moviedetails.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextWithStarsCount(voteCount: String) {
  Row(
    verticalAlignment = Alignment.CenterVertically
  ) {
    Icon(
      imageVector = Icons.Filled.Star,
      contentDescription = "star",
      tint = MaterialTheme.colorScheme.primary
    )
    Spacer(modifier = Modifier.width(5.dp))
    Text(
      text = voteCount,
      style = MaterialTheme.typography.titleSmall,
      color = MaterialTheme.colorScheme.onSurfaceVariant,
      fontSize = 15.sp,
    )
  }
}

@Composable
@Preview
fun TextWithStarsCountPreview(){
  TextWithStarsCount(
    voteCount = "4.5"
  )
}