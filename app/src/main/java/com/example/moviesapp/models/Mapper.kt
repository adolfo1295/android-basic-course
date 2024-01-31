package com.example.moviesapp.models

import com.example.moviesapp.data.local.entities.MovieEntity
import com.example.moviesapp.data.remote.responses.MovieDetailResponse
import com.example.moviesapp.data.remote.responses.MovieResponse
import com.example.moviesapp.models.detail.MovieDetailModel

const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"
fun List<MovieResponse>.toMoviesModel(): List<MovieDetailModel> {
  return this.map { movieResponse ->
    MovieDetailModel(
      title = movieResponse.title,
      imageUrl = movieResponse.posterPath,
      backdropPath = "",
      posterPath = "",
      budget = 0,
      id = movieResponse.id,
      isFavorite = false,
      overview = movieResponse.overview,
      popularity = 0.0,
      releaseDate = "",
      revenue = 0,
      status = "",
      tagline = "",
      video = false,
      voteAverage = 0.0,
      voteCount = 0
    )
  }
}

fun MovieDetailResponse.toMovieDetailModel(): MovieDetailModel {
  return MovieDetailModel(
    title = this.title,
    imageUrl = this.poster_path,
    backdropPath = this.backdrop_path,
    posterPath = this.poster_path,
    budget = this.budget,
    id = this.id,
    isFavorite = false,
    overview = this.overview,
    popularity = this.popularity,
    releaseDate = this.release_date,
    revenue = this.revenue,
    status = this.status,
    tagline = this.tagline,
    video = this.video,
    voteAverage = this.vote_average,
    voteCount = this.vote_count
  )
}

fun MovieEntity.toMovieDetail(): MovieDetailModel {
  return MovieDetailModel(
    id = this.id,
    title = this.title,
    imageUrl = this.imageUrl,
    voteCount = this.voteCount,
    video = this.video,
    voteAverage = this.voteAverage,
    tagline = this.tagline,
    status = this.status,
    revenue = this.revenue,
    releaseDate = this.releaseDate,
    popularity = this.popularity,
    overview = this.overview,
    isFavorite = false,
    budget = this.budget,
    posterPath = this.posterPath,
    backdropPath = this.backdropPath
  )
}

fun MovieDetailModel.toMovieEntity(): MovieEntity {
  return MovieEntity(
    id = this.id,
    title = this.title,
    imageUrl = this.imageUrl,
    voteCount = this.voteCount,
    video = this.video,
    voteAverage = this.voteAverage,
    tagline = this.tagline,
    status = this.status,
    revenue = this.revenue,
    releaseDate = this.releaseDate,
    popularity = this.popularity,
    overview = this.overview,
    isFavorite = false,
    budget = this.budget,
    posterPath = this.posterPath,
    backdropPath = this.backdropPath
  )
}
