package com.example.odigitaltest.ui.fragment.movies

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.model.Movie
import com.example.domain.repository.MovieRepository
import com.example.odigitaltest.core.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class MoviesViewModel(
    private val repository: MovieRepository
): BaseViewModel() {

    val postsFlow: Flow<PagingData<Movie>> = repository.getMovieList().cachedIn(viewModelScope)
}