package com.example.data.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.utils.DataMapper
import com.example.domain.model.Movie

@Entity(tableName = "cached_movies")
class MovieEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo("original_title")
    val originalTitle: String,
    val overview: String,
    val title: String,
    @ColumnInfo("poster_path")
    val posterPath: String?,
    @ColumnInfo("vote_average")
    val voteAverage: String,
    @ColumnInfo("release_date")
    val releaseDate: String,
    @ColumnInfo("backdrop_path")
    val backdropPath: String?,
    var page: Int,
) : DataMapper<Movie> {

    override fun toDomain() = Movie(
        id = id,
        originalTitle = originalTitle,
        overview = overview,
        title = title,
        posterPath = posterPath,
        voteAverage = voteAverage,
        releaseDate = releaseDate,
        backdropPath = backdropPath
    )
}