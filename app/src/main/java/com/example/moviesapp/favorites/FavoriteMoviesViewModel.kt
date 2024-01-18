package com.example.moviesapp.favorites

import androidx.lifecycle.ViewModel
import com.example.moviesapp.movieslist.ui.model.MovieModel
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
                        "Hola mundo",
                        ""
                    ),
                    MovieModel(
                        "Hola mundo 2",
                        ""
                    ),
                    MovieModel(
                        "Hola mundo 3",
                        ""
                    )
                )
            )
        }
    }

}

data class FavoriteMoviesUiState(
    val isLoading: Boolean = false,
    val moviesList: List<MovieModel> = emptyList()
)