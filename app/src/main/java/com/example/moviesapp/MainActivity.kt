package com.example.moviesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.moviesapp.navigation.Screens
import com.example.moviesapp.theme.theme.MoviesAppTheme
import com.example.moviesapp.ui.bottombar.BottomBar
import com.example.moviesapp.ui.favorites.FavoritesNavHost
import com.example.moviesapp.ui.home.HomeNavHost
import com.example.moviesapp.ui.nowplaying.NowPlayingNavHost

data class BottomNavigationItem(
  val title: String,
  val selectedIcon: ImageVector,
  val unselectedIcon: ImageVector,
  val route: String = "",
)

val itemsList = listOf(
  BottomNavigationItem(
    title = "Home",
    selectedIcon = Icons.Filled.Home,
    unselectedIcon = Icons.Outlined.Home,
    route = Screens.Home.route
  ),
  BottomNavigationItem(
    title = "Favorites",
    selectedIcon = Icons.Filled.Favorite,
    unselectedIcon = Icons.Outlined.Favorite,
    route = Screens.Favorites.route
  ),
  BottomNavigationItem(
    title = "Now playing",
    selectedIcon = Icons.Filled.Star,
    unselectedIcon = Icons.Outlined.Star,
    route = Screens.NowPlaying.route
  ),
)

class MainActivity : ComponentActivity() {
  @OptIn(ExperimentalMaterial3Api::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      MoviesAppTheme {
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        val bottomVisibilityState = rememberSaveable {
          mutableStateOf(true)
        }

        Scaffold(
          bottomBar = {
            AnimatedVisibility(visible = bottomVisibilityState.value) {
              BottomBar(
                navController = navController,
                itemsList = itemsList,
                navBackStackEntry = navBackStackEntry,
              )
            }
          }
        ) { paddingValues ->
          NavHost(
            navController = navController,
            startDestination = Screens.Home.route,
            modifier = Modifier.padding(paddingValues = paddingValues)
          ) {
            composable(Screens.Home.route) {
              Box(modifier = Modifier.fillMaxSize()) {
                HomeNavHost(
                  updateBottomVisibility = {
                    bottomVisibilityState.value = it
                  }
                )
              }
            }
            composable(Screens.Favorites.route) {
              FavoritesNavHost(
                updateBottomVisibility = {
                  bottomVisibilityState.value = it
                }
              )
            }
            composable(Screens.NowPlaying.route) {
              NowPlayingNavHost()
            }
          }
        }
      }
    }
  }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
  val navBackStackEntry by navController.currentBackStackEntryAsState()
  return navBackStackEntry?.destination?.route
}