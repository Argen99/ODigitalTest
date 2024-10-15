package com.example.odigitaltest.ui.fragment.movies.movie_details

import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.domain.model.Movie
import com.example.odigitaltest.R
import com.example.odigitaltest.core.base.BaseFragment
import com.example.odigitaltest.core.extensions.loadImage
import com.example.odigitaltest.core.extensions.showSnackbar
import com.example.odigitaltest.core.extensions.toUrl
import com.example.odigitaltest.core.utils.ImageSize
import com.example.odigitaltest.databinding.FragmentMovieDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsFragment :
    BaseFragment<FragmentMovieDetailsBinding, MovieDetailsViewModel>(R.layout.fragment_movie_details) {

    override val binding by viewBinding(FragmentMovieDetailsBinding::bind)
    override val viewModel by viewModel<MovieDetailsViewModel>()
    private val args by navArgs<MovieDetailsFragmentArgs>()
    private var currentMovie: Movie? = null

    override fun initialize() {
        viewModel.getMovieDetails(args.movieId)
    }

    override fun setupListeners() {
        binding.fabAddToFavorite.setOnClickListener { view->
            currentMovie?.let {
                viewModel.addMovieToFavorites(it)
                binding.fabAddToFavorite.setImageResource(R.drawable.ic_done)
                view.showSnackbar("Добавлено в избранные")
            }
        }
    }

    override fun launchObservers() {
        viewModel.movieState.spectateUiState(
            progressBar = binding.progressBar,
            success = { movie ->
                currentMovie = movie
                setMovieData(movie)
            }
        )
    }

    private fun setMovieData(movie: Movie): Unit = with(binding) {
        ivBackdrop.loadImage(movie.backdropPath?.toUrl(ImageSize.MEDIUM) ?: movie.posterPath?.toUrl(ImageSize.MEDIUM))
        ivPoster.loadImage(movie.posterPath?.toUrl(ImageSize.SMALL))
        tvMovieName.text = movie.title
        rbMovieRating.rating = movie.voteAverage.toFloat() / 2
        tvMovieDescription.text = movie.overview
    }
}