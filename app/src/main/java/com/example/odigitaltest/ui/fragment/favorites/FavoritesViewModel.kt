package com.example.odigitaltest.ui.fragment.favorites

import com.example.domain.model.Movie
import com.example.domain.repository.MovieRepository
import com.example.odigitaltest.core.base.BaseViewModel
import kotlinx.coroutines.flow.asStateFlow

class FavoritesViewModel(
    private val repository: MovieRepository
) : BaseViewModel() {

    private val _favoriteMoviesState = mutableUiStateFlow<List<Movie>>()
    val favoriteMoviesState = _favoriteMoviesState.asStateFlow()

    init {
        getFavoriteMovies()
    }

    private fun getFavoriteMovies() {
        repository.getFavoriteMovies().gatherRequest(_favoriteMoviesState)
    }
}