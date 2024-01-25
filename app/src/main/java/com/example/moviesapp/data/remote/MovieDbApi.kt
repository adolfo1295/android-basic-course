package com.example.moviesapp.data.remote

import com.example.moviesapp.data.remote.responses.MovieDetailResponse
import com.example.moviesapp.data.remote.responses.MovieResultResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDbApi {

    @GET("3/movie/popular")
    suspend fun getMovies(
        @Query("api_key") apiKey: String = "b6c84267a24780c397d359579bb6ad19",
        @Query("language") language: String = "es-ES",
        @Query("page") page: Int = 1
    ): MovieResultResponse

    @GET("3/movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String = "b6c84267a24780c397d359579bb6ad19",
        @Query("language") language: String = "es-ES",
    ): MovieDetailResponse

    companion object {
        const val IMAGE_URL_THUMBNAIL = "https://image.tmdb.org/t/p/w500/"
        const val IMAGE_URL_BACKDROP = "https://image.tmdb.org/t/p/original/"
    }

}