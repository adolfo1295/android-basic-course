package com.example.moviesapp.moviedetails.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.moviesapp.moviedetails.ui.MovieDetailsRoute

const val MOVIE_DETAILS_ROUTE = "movie_details_route"

fun NavController.navigateToMovieDetails() =
    navigate(MOVIE_DETAILS_ROUTE)

fun NavGraphBuilder.movieDetailsScreen() {
    composable(route = MOVIE_DETAILS_ROUTE) {
        MovieDetailsRoute()
    }
}