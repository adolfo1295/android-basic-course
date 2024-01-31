package com.example.moviesapp.ui.detail

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
import com.example.moviesapp.models.toMovieDetailModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
  private val moviesRepository: MoviesRepository
) : ViewModel() {

  private val _movieDetailsUiState = MutableStateFlow(MovieDetailsUiState())
  val movieDetailsUiState: StateFlow<MovieDetailsUiState> = _movieDetailsUiState.asStateFlow()

  init {
    viewModelScope.launch {
      moviesRepository.getFavoriteMovies().collect { favoriteMovies ->
        _movieDetailsUiState.update {
          it.copy(
            favoriteMovies = favoriteMovies
          )
        }
      }
    }
  }

  fun getMovieDetails(movieId: String) {
    viewModelScope.launch {
      try {
        val movieDetail = moviesRepository.getMovieDetail(movieId = movieId)
        movieDetail.let { movie ->
          _movieDetailsUiState.update {
            it.copy(
              movie = movie.toMovieDetailModel(),
              isLoading = false,
            )
          }
        }
      } catch (e: Exception) {
        e.printStackTrace()
      }
    }
  }

  fun updateFavorites(movieDetailModel: MovieDetailModel, isMovieFavorite: Boolean) {
    viewModelScope.launch {
      if (isMovieFavorite) {
        moviesRepository.removeMovieFromFavorites(movieModel = movieDetailModel)
      } else {
        moviesRepository.insertMovieToFavorites(movieModel = movieDetailModel)
      }
    }
  }

  companion object {
    val Factory: ViewModelProvider.Factory = viewModelFactory {
      initializer {
        MovieDetailsViewModel(
          MoviesRepository(
            movieDbApi = RetrofitClient.service,
            dao = MoviesApp.database.movieDao()
          )
        )
      }
    }
  }
}

data class MovieDetailsUiState(
  val isLoading: Boolean = true,
  val movie: MovieDetailModel? = null,
  val favoriteMovies: List<MovieEntity> = emptyList()
)