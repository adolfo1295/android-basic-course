package com.example.moviesapp.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
  @PrimaryKey(autoGenerate = true) val uid: Int,
  @ColumnInfo(name = "title") val title: String,
)
