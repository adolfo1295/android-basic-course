package com.example.moviesapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDbApi {

  @GET("3/movie/popular")
  suspend fun getMovies(
    @Query("api_key") apiKey: String = "b6c84267a24780c397d359579bb6ad19",
    @Query("language") language: String = "es-ES",
    @Query("page") page: Int = 1
  ): MovieResultResponse


}