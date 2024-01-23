package com.example.moviesapp.favorites

import androidx.lifecycle.ViewModel
import com.example.moviesapp.models.MovieModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FavoriteMoviesViewModel : ViewModel() {

    private val _favoritesMoviesUiState = MutableStateFlow(FavoriteMoviesUiState())
    val favoritesMoviesUiState: StateFlow<FavoriteMoviesUiState> =
        _favoritesMoviesUiState.asStateFlow()

    init {
        getFavoriteMovies()
    }

    fun getFavoriteMovies() {
        //TODO ADD REPO IMPLEMENTATION WITH FAV DATA
        _favoritesMoviesUiState.update {
            it.copy(
                moviesList = listOf(
                    MovieModel(
                        id = "1",
                        title = "hola 1",
                        imageUrl = "",
                    ),
                    MovieModel(
                        id = "2",
                        title = "hola 2",
                        imageUrl = "",
                    ),
                    MovieModel(
                        id = "3",
                        title = "hola 3",
                        imageUrl = "",
                    ),
                )
            )
        }
    }

}

data class FavoriteMoviesUiState(
    val isLoading: Boolean = false,
    val moviesList: List<MovieModel> = emptyList()
)