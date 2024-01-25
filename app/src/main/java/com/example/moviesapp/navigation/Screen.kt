package com.example.moviesapp.navigation

sealed class Screens(val route : String) {
  object Home : Screens("home_route")
  object Favorites : Screens("favorites_route")
  object NowPlaying : Screens("now_playing_route")
  object Details : Screens("details_route/")
}