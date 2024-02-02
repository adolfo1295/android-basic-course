package com.example.moviesapp.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.moviesapp.MoviesApp
import com.example.moviesapp.data.remote.RetrofitClient
import com.example.moviesapp.data.repositories.MoviesRepository
import com.example.moviesapp.models.detail.MovieDetailModel
import com.example.moviesapp.models.toMovieDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoriteMoviesViewModel(
  private val moviesRepository: MoviesRepository
) : ViewModel() {

  private var _favoritesMovieUiState = MutableStateFlow(FavoriteMoviesUiState())
  val favoriteMoviesUiState: StateFlow<FavoriteMoviesUiState> = _favoritesMovieUiState.asStateFlow()

  init {
    getFavoriteMovies()
  }


  private fun getFavoriteMovies() {
    viewModelScope.launch {
      moviesRepository.getFavoriteMovies().collectLatest { favoriteMovies ->
        _favoritesMovieUiState.update {
          it.copy(favoriteMovies = favoriteMovies.map { movieEntity ->
            movieEntity.toMovieDetail()
          })
        }
      }
    }
  }

  fun removeFavoriteMovie(movie: MovieDetailModel){
    viewModelScope.launch {
      moviesRepository.removeMovieFromFavorites(movie)
    }
  }

  companion object {
    val Factory: ViewModelProvider.Factory = viewModelFactory {
      initializer {
        FavoriteMoviesViewModel(
          MoviesRepository(
            movieDbApi = RetrofitClient.service,
            dao = MoviesApp.database.movieDao()
          )
        )
      }
    }
  }
}

data class FavoriteMoviesUiState(
  val isLoading: Boolean = false,
  val favoriteMovies: List<MovieDetailModel> = emptyList()
)