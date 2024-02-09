package com.example.moviesapp.nowplaying

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.moviesapp.MoviesApp
import com.example.moviesapp.data.remote.RetrofitClient
import com.example.moviesapp.data.repositories.MoviesRepository
import com.example.moviesapp.models.detail.MovieDetailModel
import com.example.moviesapp.models.toMoviesModel
import com.example.moviesapp.movieslist.ui.MoviesUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NowPlayingViewModel(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val _moviesListUiState = MutableStateFlow(MoviesUiState())
    val moviesListUiState: StateFlow<MoviesUiState> = _moviesListUiState.asStateFlow()

    init {
        getMovies()
        getFavoriteMovies()
    }

    fun getMovies() {
        viewModelScope.launch {
            try {
                val movies = moviesRepository.getNowPlayingMovies()
                _moviesListUiState.update { moviesUiState ->
                    moviesUiState.copy(
                        moviesList = movies.toMoviesModel(),
                        isLoading = false,
                        showErrorMessage = false
                    )
                }
            } catch (e: Exception) {
                _moviesListUiState.update {
                    it.copy(
                        showErrorMessage = true
                    )
                }
            }
        }
    }

    private fun getFavoriteMovies() {
        viewModelScope.launch {
            moviesRepository.getFavoriteMovies().collectLatest { favoriteList ->
                _moviesListUiState.update {
                    it.copy(
                        favoriteMovies = favoriteList
                    )
                }
            }
        }
    }

    fun onFavoriteMovieClick(movieModel: MovieDetailModel, isMovieInFavorites: Boolean) {
        viewModelScope.launch {
            if (isMovieInFavorites) {
                moviesRepository.removeMovieFromFavorites(movieModel = movieModel)
            } else {
                moviesRepository.insertMovieToFavorites(movieModel = movieModel)
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                NowPlayingViewModel(
                    MoviesRepository(
                        movieDbApi = RetrofitClient.service,
                        dao = MoviesApp.database.movieDao()
                    )
                )
            }
        }
    }

}