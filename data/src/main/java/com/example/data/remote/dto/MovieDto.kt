package com.example.data.remote.dto

import com.example.data.local.room.entity.MovieEntity
import com.example.data.utils.DataMapper
import com.example.domain.model.Movie
import com.google.gson.annotations.SerializedName

data class MovieDto(
    val id: Int,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    val title: String,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("vote_average")
    val voteAverage: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
) : DataMapper<Movie> {

    override fun toDomain() = Movie(
        id = id,
        originalTitle = originalTitle,
        overview = overview,
        title = title,
        posterPath = posterPath,
        voteAverage = voteAverage,
        releaseDate = releaseDate,
        backdropPath = backdropPath,
    )
}

fun MovieDto.toEntity() = MovieEntity(
    id = id,
    originalTitle = originalTitle,
    overview = overview,
    title = title,
    posterPath = posterPath,
    voteAverage = voteAverage,
    releaseDate = releaseDate,
    backdropPath = backdropPath,
    page = 1
)