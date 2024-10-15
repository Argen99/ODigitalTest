package com.example.domain.model

data class Movie(
    val id: Int,
    val originalTitle: String,
    val overview: String,
    val title: String,
    val posterPath: String?,
    val voteAverage: String,
    val releaseDate: String,
    val backdropPath: String?,
)