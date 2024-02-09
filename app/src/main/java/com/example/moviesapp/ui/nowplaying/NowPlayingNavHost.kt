package com.example.moviesapp.ui.nowplaying

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.moviesapp.navigation.Screens
import com.example.moviesapp.nowplaying.NowPlayingRoute
import com.example.moviesapp.ui.detail.MovieDetailsRoute

@Composable
fun NowPlayingNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.NowPlaying.route) {
        composable(Screens.NowPlaying.route) {
            NowPlayingRoute(onMovieClick = {
                navController.navigate(Screens.Details.route + it)
            })
        }

        composable(
            route = "${Screens.Details.route}{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.StringType })
        ) {
            val movieId = it.arguments?.getString("movieId")
            MovieDetailsRoute(movieId = movieId.orEmpty(), onPopUp = {
                navController.popBackStack()
            })
        }
    }
}
