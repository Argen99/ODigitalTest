package com.example.data.remote.api_service

import com.example.data.remote.dto.MovieDto
import com.example.data.remote.dto.MovieListResponse
import com.example.data.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET("3/movie/popular")
    suspend fun getMovieList(
        @Query("language") language: String = Constants.DEFAULT_LANGUAGE,
        @Query("page") page: Int
    ): MovieListResponse

    @GET("3/movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = Constants.DEFAULT_LANGUAGE
    ): MovieDto
}