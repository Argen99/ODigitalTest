package com.example.odigitaltest.ui.fragment.movies

import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.odigitaltest.R
import com.example.odigitaltest.core.base.BaseFragment
import com.example.odigitaltest.core.extensions.safeNavigation
import com.example.odigitaltest.databinding.FragmentMoviesBinding
import com.example.odigitaltest.ui.adapter.MoviePagingAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment :
    BaseFragment<FragmentMoviesBinding, MoviesViewModel>(R.layout.fragment_movies) {

    override val binding by viewBinding(FragmentMoviesBinding::bind)
    override val viewModel by viewModel<MoviesViewModel>()
    private val movieAdapter: MoviePagingAdapter by lazy {
        MoviePagingAdapter(::onItemClick)
    }

    override fun initialize() {
        binding.rvMovie.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = movieAdapter
        }
    }

    override fun launchObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.postsFlow.collect { pagingData ->
                movieAdapter.submitData(pagingData)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            movieAdapter.loadStateFlow.collectLatest { loadState ->
                binding.progressBar.isVisible =
                    (loadState.refresh is LoadState.Loading) || (loadState.append is LoadState.Loading)
            }
        }
    }

    private fun onItemClick(id: Int) {
        findNavController().safeNavigation(
            MoviesFragmentDirections.actionMoviesFragmentToMovieDetailsFragment(id)
        )
    }
}