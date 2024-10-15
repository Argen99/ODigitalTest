package com.example.odigitaltest.ui.fragment.favorites

import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.odigitaltest.R
import com.example.odigitaltest.core.base.BaseFragment
import com.example.odigitaltest.core.extensions.showSnackbar
import com.example.odigitaltest.databinding.FragmentFavoritesBinding
import com.example.odigitaltest.ui.adapter.FavoriteMoviesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment :
    BaseFragment<FragmentFavoritesBinding, FavoritesViewModel>(R.layout.fragment_favorites) {

    override val binding by viewBinding(FragmentFavoritesBinding::bind)
    override val viewModel by viewModel<FavoritesViewModel>()
    private val favoriteMoviesAdapter: FavoriteMoviesAdapter by lazy {
        FavoriteMoviesAdapter()
    }

    override fun initialize() {
        binding.rvFavoriteMovies.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = favoriteMoviesAdapter
        }
    }

    override fun launchObservers() {
        viewModel.favoriteMoviesState.spectateUiState(
            progressBar = binding.progressBar,
            loading = {
            },
            success = {
                favoriteMoviesAdapter.submitList(it)
            },
            error = {
                view?.showSnackbar(it)
            }
        )
    }
}