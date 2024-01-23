package com.example.moviesapp.movieslist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.moviesapp.data.remote.RetrofitClient
import com.example.moviesapp.data.repositories.MoviesRepository
import com.example.moviesapp.models.MovieModel
import com.example.moviesapp.models.toMoviesModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MoviesListViewModel(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val _moviesListUiState = MutableStateFlow(MoviesUiState())
    val moviesListUiState: StateFlow<MoviesUiState> = _moviesListUiState.asStateFlow()

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            val movies = moviesRepository.getMovies()
            _moviesListUiState.update { moviesUiState ->
                moviesUiState.copy(
                    moviesList = movies.toMoviesModel(),
                    isLoading = false
                )
            }
        }
    }

    fun onFavoriteMovieClick(movieModel: MovieModel) {

        _moviesListUiState.update { moviesUiState ->
            val auxList = moviesUiState.moviesList.toList()
            auxList.find {
                it == movieModel
            }?.let {
                it.isFavorite = !it.isFavorite
            }

            moviesUiState.copy(
                moviesList = auxList,
                //isLoading = !moviesUiState.isLoading
            )
        }
        //TODO do the repo call to save the movie into favorites DB
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                MoviesListViewModel(
                    MoviesRepository(
                        movieDbApi = RetrofitClient.service
                    )
                )
            }
        }
    }
}

data class MoviesUiState(
    val moviesList: List<MovieModel> = emptyList(),
    val isLoading: Boolean = false,
)