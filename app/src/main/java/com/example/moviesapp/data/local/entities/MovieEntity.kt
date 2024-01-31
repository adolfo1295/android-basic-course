package com.example.moviesapp.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
  @PrimaryKey(autoGenerate = false) val id: Int,
  @ColumnInfo(name = "title") val title: String,
  @ColumnInfo(name = "imageUrl") val imageUrl: String,
  @ColumnInfo(name = "isFavorite") var isFavorite: Boolean = false,
  @ColumnInfo(name = "backdropPath") val backdropPath: String,
  @ColumnInfo(name = "budget") val budget: Int,
  @ColumnInfo(name = "overview") val overview: String,
  @ColumnInfo(name = "popularity") val popularity: Double,
  @ColumnInfo(name = "posterPath") val posterPath: String,
  @ColumnInfo(name = "releaseDate") val releaseDate: String,
  @ColumnInfo(name = "revenue") val revenue: Int,
  @ColumnInfo(name = "status") val status: String,
  @ColumnInfo(name = "tagline") val tagline: String,
  @ColumnInfo(name = "video") val video: Boolean,
  @ColumnInfo(name = "voteAverage") val voteAverage: Double,
  @ColumnInfo(name = "voteCount") val voteCount: Int
)
