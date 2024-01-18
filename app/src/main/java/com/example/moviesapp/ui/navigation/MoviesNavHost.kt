package com.example.moviesapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.moviesapp.moviedetails.ui.navigation.movieDetailsScreen
import com.example.moviesapp.moviedetails.ui.navigation.navigateToMovieDetails
import com.example.moviesapp.movieslist.ui.navigation.MOVIES_LIST_ROUTE
import com.example.moviesapp.movieslist.ui.navigation.moviesListScreen

@Composable
fun MoviesNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MOVIES_LIST_ROUTE
    ) {

        moviesListScreen(
            onMovieClick = navController::navigateToMovieDetails
        )

        movieDetailsScreen()

    }

}