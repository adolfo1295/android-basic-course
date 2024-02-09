package com.example.moviesapp.data.remote

import com.example.moviesapp.data.remote.responses.MovieDetailResponse
import com.example.moviesapp.data.remote.responses.MovieResultResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDbApi {

    @GET("3/movie/popular")
    suspend fun getMovies(
        @Query("language") language: String = "es-ES",
        @Query("page") page: Int = 1
    ): MovieResultResponse

    @GET("3/movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: String,
        @Query("language") language: String = "es-ES",
    ): MovieDetailResponse

    @GET("3/movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("language") language: String = "es-ES",
        @Query("page") page: Int = 1,
    ): MovieResultResponse

    companion object {
        const val IMAGE_URL_THUMBNAIL = "https://image.tmdb.org/t/p/w300/"
        const val IMAGE_URL_HOME = "https://image.tmdb.org/t/p/w400/"
        const val IMAGE_URL_BACKDROP = "https://image.tmdb.org/t/p/original/"
    }

}