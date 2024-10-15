package com.example.data.local.room.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.room.entity.MovieEntity

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM cached_movies Order By page")
    fun getAllMovies(): PagingSource<Int, MovieEntity>

    @Query("DELETE FROM cached_movies")
    suspend fun clearMoviesAll()
}