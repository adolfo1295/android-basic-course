package com.example.moviesapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.moviesapp.data.local.entities.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

  @Insert
  suspend fun insertMovieToFavorites(movieEntity: MovieEntity)

  @Delete
  suspend fun removeMoviesFromFavorites(movieEntity: MovieEntity)

  @Query("select * from movieentity")
  fun getUserFavoriteMovies(): Flow<List<MovieEntity>>

  @Query("select * from movieentity where id = :movieId")
  fun getFavoriteMovieById(movieId: String): Flow<MovieEntity>

}