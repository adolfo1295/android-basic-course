package com.example.moviesapp.ui.bottombar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.moviesapp.BottomNavigationItem

@Composable
fun BottomBar(
  navController: NavController,
  itemsList: List<BottomNavigationItem>,
  navBackStackEntry: NavBackStackEntry?,
) {
  var selectedItemIndex by rememberSaveable {
    mutableIntStateOf(0)
  }
  NavigationBar {
    itemsList.forEachIndexed { index, bottomNavigationItem ->
      val isSelected = bottomNavigationItem.route ==
              navBackStackEntry?.destination?.route
      NavigationBarItem(
        label = {
          Text(text = bottomNavigationItem.title)
        },
        selected = isSelected,
        onClick = {
          selectedItemIndex = index
          navController.navigate(bottomNavigationItem.route) {
            popUpTo(navController.graph.findStartDestination().id) {
              saveState = true
            }
            launchSingleTop = true
            restoreState = true
          }
        },
        icon = {
          Icon(
            imageVector = bottomNavigationItem.selectedIcon,
            contentDescription = bottomNavigationItem.title
          )
        })
    }
  }
}