package com.example.moviesapp.movieslist.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviesapp.R
import com.example.moviesapp.movieslist.ui.model.MovieModel

@Composable
fun MovieCard(
    movie: MovieModel
) {
    Card(
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
        )

        Text(
            text = movie.name,
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        )
    }
}

@Composable
@Preview
fun MovieCardPreview() {
    MovieCard(MovieModel("Hola mundo"))
}