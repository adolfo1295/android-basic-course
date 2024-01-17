package com.example.moviesapp.movieslist.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.moviesapp.movieslist.ui.model.MovieModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieCard(
    movie: MovieModel,
    onMovieClick: () -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier.padding(8.dp),
        onClick = onMovieClick
    ) {
        AsyncImage(
            model = movie.imageUrl,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
        )

        Text(
            text = movie.title,
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
    MovieCard(
        movie = MovieModel("Hola mundo",""),
        onMovieClick = {}
    )
}