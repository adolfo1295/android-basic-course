package com.example.moviesapp.movieslist.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.moviesapp.movieslist.ui.MoviesListRoute

const val MOVIES_LIST_ROUTE = "movies_list_route"

fun NavGraphBuilder.moviesListScreen(
    onMovieClick: (String) -> Unit
) {
    composable(route = MOVIES_LIST_ROUTE) {
        MoviesListRoute(
            onMovieClick = { movieId ->
                onMovieClick(movieId)
            }
        )
    }
}