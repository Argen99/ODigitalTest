package com.example.data.remote.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.data.local.room.dao.FavoritesDao
import com.example.data.local.room.dao.MovieDao
import com.example.data.local.room.data_base.MovieDB
import com.example.data.local.room.entity.toFavoriteEntity
import com.example.data.remote.api_service.MovieApiService
import com.example.data.remote.paging_src.MoviesRemoteMediator
import com.example.data.utils.makeNetworkRequest
import com.example.data.utils.makeRequest
import com.example.domain.model.Movie
import com.example.domain.repository.MovieRepository
import com.example.domain.utils.Either
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepositoryImpl(
    private val movieApiService: MovieApiService,
    private val movieDB: MovieDB,
    private val movieDao: MovieDao,
    private val favoritesDao: FavoritesDao,
) : MovieRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getMovieList(): Flow<PagingData<Movie>> {
        val pagingSourceFactory = { movieDao.getAllMovies() }

        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = MoviesRemoteMediator(movieApiService, movieDB),
            pagingSourceFactory = pagingSourceFactory
        ).flow
            .map { pagingData ->
                pagingData.map { it.toDomain() }
            }
    }

    override fun getMovieDetails(movieId: Int): Flow<Either<String, Movie>> =
        makeNetworkRequest {
            movieApiService.getMovieDetails(movieId).toDomain()
        }

    override suspend fun insertFavoriteMovie(movie: Movie) {
        favoritesDao.insertFavoriteMovie(movie.toFavoriteEntity())
    }

    override fun getFavoriteMovies(): Flow<Either<String, List<Movie>>> = makeRequest {
        favoritesDao.getFavoriteMovies().map { movieList -> movieList.map { movie -> movie.toDomain() } }
    }
}