package com.example.moviesapp.movieslist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.moviesapp.MoviesApp
import com.example.moviesapp.data.local.entities.MovieEntity
import com.example.moviesapp.data.remote.RetrofitClient
import com.example.moviesapp.data.repositories.MoviesRepository
import com.example.moviesapp.models.detail.MovieDetailModel
import com.example.moviesapp.models.toMoviesModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MoviesListViewModel(
  private val moviesRepository: MoviesRepository,
) : ViewModel() {

  private val _moviesListUiState = MutableStateFlow(MoviesUiState())
  val moviesListUiState: StateFlow<MoviesUiState> = _moviesListUiState.asStateFlow()

  init {
    getMovies()
    getFavoriteMovies()
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

  fun getMovies() {
    viewModelScope.launch {
      try {
        val movies = moviesRepository.getMovies()
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
        MoviesListViewModel(
          MoviesRepository(
            movieDbApi = RetrofitClient.service,
            dao = MoviesApp.database.movieDao()
          )
        )
      }
    }
  }
}

data class MoviesUiState(
  val moviesList: List<MovieDetailModel> = emptyList(),
  val isLoading: Boolean = false,
  val showErrorMessage: Boolean = false,
  val favoriteMovies: List<MovieEntity> = emptyList()
)
