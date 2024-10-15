package com.example.odigitaltest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Movie
import com.example.odigitaltest.core.extensions.loadImage
import com.example.odigitaltest.core.extensions.toUrl
import com.example.odigitaltest.databinding.ItemMovieBinding

class MoviePagingAdapter(
    private val onItemCLick: (id: Int) -> Unit,
) : PagingDataAdapter<Movie, MoviePagingAdapter.AnimeViewHolder>(diffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AnimeViewHolder(
        ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class AnimeViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: Movie) = with(binding) {
            tvText.text = item.title
            item.posterPath?.let { poster ->
                ivPoster.loadImage(poster.toUrl())
            }
            ratingBar.rating = item.voteAverage.toFloat() / 2
        }

        init {
            itemView.setOnClickListener {
                getItem(absoluteAdapterPosition)?.id?.let { id -> onItemCLick(id) }
            }
        }
    }

    companion object {
        val diffCallBack = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }
}