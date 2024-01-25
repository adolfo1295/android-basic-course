package com.example.moviesapp.moviedetails.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.moviesapp.moviedetails.ui.MovieDetailsRoute
import com.example.moviesapp.movieslist.ui.navigation.MOVIES_LIST_ROUTE
import com.example.moviesapp.navigation.Screens

fun NavController.navigateToMovieDetails(movieId: String) {
    navigate(Screens.Details.route + movieId){
        popUpTo(MOVIES_LIST_ROUTE)
    }
}

fun NavGraphBuilder.movieDetailsScreen(
    onPopUp: () -> Unit
) {
    composable(
        route = "${Screens.Details.route}{movieId}",
        arguments = listOf(navArgument("movieId") { type = NavType.StringType })
    ) { navBackStackEntry ->
        val movieId = navBackStackEntry.arguments?.getString("movieId")
        MovieDetailsRoute(
            movieId = movieId.orEmpty(),
            onPopUp = onPopUp
        )
    }
}