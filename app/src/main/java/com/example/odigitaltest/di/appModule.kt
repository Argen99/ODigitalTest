package com.example.odigitaltest.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import com.example.odigitaltest.ui.fragment.movies.MoviesViewModel
import com.example.odigitaltest.ui.fragment.movies.movie_details.MovieDetailsViewModel
import com.example.odigitaltest.ui.fragment.favorites.FavoritesViewModel
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::MoviesViewModel)
    viewModelOf(::FavoritesViewModel)
    viewModelOf(::MovieDetailsViewModel)
}