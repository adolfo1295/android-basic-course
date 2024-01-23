package com.example.moviesapp.moviedetails.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
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

    fun getMovieDetails(movieId: String) {
        _movieDetailsUiState.update {
            it.copy(
                movie = null,
                isLoading = true
            )
        }
        viewModelScope.launch {
            try {
                val movieDetail = moviesRepository.getMovieDetail(movieId = movieId)
                movieDetail.let { movie ->
                    _movieDetailsUiState.update {
                        it.copy(
                            movie = movie.toMovieDetailModel(),
                            isLoading = false
                        )
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                MovieDetailsViewModel(
                    MoviesRepository(
                        movieDbApi = RetrofitClient.service
                    )
                )
            }
        }
    }

}

data class MovieDetailsUiState(
    val isLoading: Boolean = false,
    val movie: MovieDetailModel? = null,
)