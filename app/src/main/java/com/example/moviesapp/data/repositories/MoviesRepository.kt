package com.example.moviesapp.data.repositories

import com.example.moviesapp.data.local.dao.MovieDao
import com.example.moviesapp.data.local.entities.MovieEntity
import com.example.moviesapp.data.remote.MovieDbApi
import com.example.moviesapp.data.remote.responses.MovieDetailResponse
import com.example.moviesapp.data.remote.responses.MovieResponse
import com.example.moviesapp.models.detail.MovieDetailModel
import com.example.moviesapp.models.toMovieEntity
import kotlinx.coroutines.flow.Flow

class MoviesRepository(
  private val movieDbApi: MovieDbApi,
  private val dao: MovieDao
) {
  suspend fun getMovies(): List<MovieResponse> {
    return movieDbApi.getMovies().results
  }

  suspend fun getMovieDetail(movieId: String): MovieDetailResponse {
    return movieDbApi.getMovieDetail(movieId = movieId)
  }

  suspend fun insertMovieToFavorites(movieModel: MovieDetailModel) {
    dao.insertMovieToFavorites(movieEntity = movieModel.toMovieEntity())
  }

  suspend fun removeMovieFromFavorites(movieModel: MovieDetailModel) {
    dao.removeMoviesFromFavorites(movieModel.toMovieEntity())
  }

  fun getFavoriteMovies(): Flow<List<MovieEntity>> {
    return dao.getUserFavoriteMovies()
  }
}