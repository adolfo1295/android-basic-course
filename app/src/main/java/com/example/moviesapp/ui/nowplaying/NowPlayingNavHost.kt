package com.example.moviesapp.ui.nowplaying

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviesapp.navigation.Screens

@Composable
fun NowPlayingNavHost() {
  val navController = rememberNavController()
  NavHost(navController = navController, startDestination = Screens.NowPlaying.route) {
    composable(Screens.NowPlaying.route) {
      Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Now playing Screen WIP")
      }
    }
  }
}
