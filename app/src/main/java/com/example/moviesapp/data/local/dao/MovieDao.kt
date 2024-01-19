package com.example.moviesapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.moviesapp.data.local.entities.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

  @Insert
  suspend fun insertMovie(movieEntity: MovieEntity)

  @Query("select * from movieentity")
  fun getUserFavorites(): Flow<List<MovieEntity>>

}