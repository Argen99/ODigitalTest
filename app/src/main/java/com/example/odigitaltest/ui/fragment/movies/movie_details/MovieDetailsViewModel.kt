package com.example.odigitaltest.ui.fragment.movies.movie_details

import androidx.lifecycle.viewModelScope
import com.example.domain.model.Movie
import com.example.domain.repository.MovieRepository
import com.example.odigitaltest.core.base.BaseViewModel
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val repository: MovieRepository
): BaseViewModel() {

    private val _movieState = mutableUiStateFlow<Movie>()
    val movieState = _movieState.asStateFlow()

    fun getMovieDetails(movieId: Int) {
        repository.getMovieDetails(movieId).gatherRequest(_movieState)
    }

    fun addMovieToFavorites(movie: Movie) {
        viewModelScope.launch {
            repository.insertFavoriteMovie(movie)
        }
    }
}