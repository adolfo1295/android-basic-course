package com.example.moviesapp.movieslist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.moviesapp.movieslist.data.MoviesRepository
import com.example.moviesapp.movieslist.data.remote.RetrofitClient
import com.example.moviesapp.movieslist.ui.model.MovieModel
import com.example.moviesapp.movieslist.ui.model.toMoviesModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MoviesListViewModel(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val _moviesListUiState = MutableStateFlow(MoviesUiState())
    val moviesListUiState : StateFlow<MoviesUiState> = _moviesListUiState.asStateFlow()

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
    val moviesList : List<MovieModel> = emptyList(),
    val isLoading : Boolean = false,
)