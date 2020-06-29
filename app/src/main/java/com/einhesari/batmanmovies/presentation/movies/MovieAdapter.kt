package com.einhesari.batmanmovies.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.einhesari.batmanmovies.R
import com.einhesari.batmanmovies.databinding.MovieItemBinding
import com.einhesari.batmanmovies.presentation.model.BatmanMovie
import com.jakewharton.rxrelay2.PublishRelay
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter :
    ListAdapter<BatmanMovie, MovieAdapter.MovieViewHolder>(
        DIFF_CALLBACK()
    ) {

    private val onItemClick = PublishRelay.create<BatmanMovie>()

    class DIFF_CALLBACK : DiffUtil.ItemCallback<BatmanMovie>() {
        override fun areItemsTheSame(oldItem: BatmanMovie, newItem: BatmanMovie): Boolean {
            return oldItem.title.equals(newItem.title)
        }

        override fun areContentsTheSame(oldItem: BatmanMovie, newItem: BatmanMovie): Boolean {
            return oldItem.title.equals(newItem.title)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.movie_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.binding.movie = movie
        holder.itemView.setOnClickListener {
            onItemClick.accept(movie)
        }
    }

    fun selectedMovie() = onItemClick.hide()


    class MovieViewHolder(val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.title_tv.isSelected = true
        }
    }
}