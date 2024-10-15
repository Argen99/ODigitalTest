package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.model.Movie
import com.example.domain.utils.Either
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovieList(): Flow<PagingData<Movie>>
    fun getMovieDetails(movieId: Int): Flow<Either<String, Movie>>
    suspend fun insertFavoriteMovie(movie: Movie)
    fun getFavoriteMovies(): Flow<Either<String, List<Movie>>>
}