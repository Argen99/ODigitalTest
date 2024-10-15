package com.example.odigitaltest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.domain.model.Movie
import com.example.odigitaltest.core.extensions.loadImage
import com.example.odigitaltest.core.extensions.toUrl
import com.example.odigitaltest.databinding.ItemMovieBinding

class FavoriteMoviesAdapter :
    ListAdapter<Movie, FavoriteMoviesAdapter.FavoriteMoviesViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMoviesViewHolder {
        return FavoriteMoviesViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoriteMoviesViewHolder, position: Int) {
        getItem(position)?.let { item->
            holder.onBind(item)
        }
    }

    class FavoriteMoviesViewHolder(private val binding: ItemMovieBinding) : ViewHolder(binding.root) {
        fun onBind(item: Movie): Unit = with(binding) {
            tvText.text = item.title
            item.posterPath?.let { poster ->
                ivPoster.loadImage(poster.toUrl())
            }
            ratingBar.rating = item.voteAverage.toFloat() / 2
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }
}