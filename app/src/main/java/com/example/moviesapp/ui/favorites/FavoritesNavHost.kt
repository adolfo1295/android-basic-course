package com.example.moviesapp.ui.favorites

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviesapp.favorites.FavoritesRoute
import com.example.moviesapp.navigation.Screens

@Composable
fun FavoritesNavHost() {
  val favoritesNavController = rememberNavController()
  NavHost(navController = favoritesNavController, startDestination = Screens.Favorites.route) {
    composable(route = Screens.Favorites.route) {
      FavoritesRoute()
    }
  }
}