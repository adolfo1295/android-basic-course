package com.example.moviesapp.ui.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.moviesapp.movieslist.ui.MoviesListRoute
import com.example.moviesapp.navigation.Screens
import com.example.moviesapp.ui.detail.MovieDetailsRoute

@Composable
fun HomeNavHost(
  updateBottomVisibility: (showBottom: Boolean) -> Unit
) {
  val navController = rememberNavController()

  NavHost(navController = navController, startDestination = Screens.Home.route) {

    navController.addOnDestinationChangedListener { nav, dest, _ ->

      if (dest.route?.contains(Screens.Details.route) == true) {
        updateBottomVisibility(false)
      } else {
        updateBottomVisibility(true)
      }
    }


    composable(Screens.Home.route) {
      MoviesListRoute(onMovieClick = {
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