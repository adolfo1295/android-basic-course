package com.example.moviesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviesapp.data.local.dao.MovieDao
import com.example.moviesapp.data.local.entities.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MoviesDatabase: RoomDatabase() {
  abstract fun movieDao(): MovieDao
}