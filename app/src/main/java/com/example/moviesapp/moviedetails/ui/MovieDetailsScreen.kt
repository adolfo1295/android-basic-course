package com.example.moviesapp.moviedetails.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun MovieDetailsRoute(){
    MovieDetailsScreen()
}
@Composable
fun MovieDetailsScreen() {
    Text(
        "HOLA MUNDO",
        modifier = Modifier.fillMaxSize(),
        textAlign = TextAlign.Center
    )
    //TODO Add movie details screen
}