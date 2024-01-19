package com.example.moviesapp

import android.app.Application
import androidx.room.Room
import com.example.moviesapp.data.local.MoviesDatabase

class MoviesApp: Application() {

  companion object {
    lateinit var database: MoviesDatabase
  }

  override fun onCreate() {
    super.onCreate()
    database = Room.databaseBuilder(
      applicationContext,
      MoviesDatabase::class.java,
      "movie_db"
    ).build()
  }
}
