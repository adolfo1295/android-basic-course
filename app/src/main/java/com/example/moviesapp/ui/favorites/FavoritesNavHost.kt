package com.example.moviesapp.ui.favorites

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.moviesapp.favorites.FavoritesRoute
import com.example.moviesapp.navigation.Screens
import com.example.moviesapp.ui.detail.MovieDetailsRoute

@Composable
fun FavoritesNavHost(
  updateBottomVisibility: (showBottom: Boolean) -> Unit
) {
  val favoritesNavController = rememberNavController()
  NavHost(navController = favoritesNavController, startDestination = Screens.Favorites.route) {

    favoritesNavController.addOnDestinationChangedListener { nav, dest, _ ->
      if (dest.route?.contains(Screens.Details.route) == true) {
        updateBottomVisibility(false)
      } else {
        updateBottomVisibility(true)
      }
    }

    composable(route = Screens.Favorites.route) {
      FavoritesRoute() {
        favoritesNavController.navigate(Screens.Details.route + it)
      }
    }

    composable(
      route = "${Screens.Details.route}{movieId}",
      arguments = listOf(navArgument("movieId") { type = NavType.StringType })
    ) {

      val movieId = it.arguments?.getString("movieId")
      MovieDetailsRoute(movieId = movieId.orEmpty(), onPopUp = {
        favoritesNavController.popBackStack()
      })
    }
  }
}