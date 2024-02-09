package com.example.moviesapp.nowplaying

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moviesapp.models.detail.MovieDetailModel
import com.example.moviesapp.movieslist.ui.MovieCard
import com.example.moviesapp.movieslist.ui.MovieError
import com.example.moviesapp.movieslist.ui.MoviesUiState

@Composable
fun NowPlayingRoute(
    onMovieClick: (Int) -> Unit,
    viewModel: NowPlayingViewModel = viewModel(
        factory = NowPlayingViewModel.Factory
    )
) {

    val moviesUiState by viewModel.moviesListUiState.collectAsStateWithLifecycle()

    NowPlayingScreen(
        moviesUiState = moviesUiState,
        onMovieClick = onMovieClick,
        onFavoriteClick = { movie, isMovieInFavorites ->
            viewModel.onFavoriteMovieClick(
                movieModel = movie,
                isMovieInFavorites = isMovieInFavorites
            )
        },
        onRefreshClick = viewModel::getMovies
    )
}

@Composable
fun NowPlayingScreen(
    moviesUiState: MoviesUiState,
    onMovieClick: (Int) -> Unit,
    onFavoriteClick: (movie: MovieDetailModel, isMovieInFavorites: Boolean) -> Unit,
    onRefreshClick: () -> Unit
) {


    when {
        moviesUiState.isLoading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(50.dp)
                )
            }
        }

        moviesUiState.showErrorMessage -> {
            MovieError {
                onRefreshClick()
            }
        }

        else -> {

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                verticalItemSpacing = 5.dp,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                content = {
                    items(moviesUiState.moviesList) { movieModel ->
                        val isMovieFavorites = moviesUiState.favoriteMovies.firstOrNull {
                            it.id == movieModel.id
                        }?.isFavorite ?: false

                        MovieCard(
                            movie = movieModel,
                            onMovieClick = {
                                onMovieClick(movieModel.id)
                            },
                            onFavoriteClick = { movie, isMovieInFavorites ->
                                onFavoriteClick(movie, isMovieInFavorites)
                            },
                            isMovieFavorite = isMovieFavorites
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 2.dp, vertical = 4.dp)
            )
        }
    }
}

