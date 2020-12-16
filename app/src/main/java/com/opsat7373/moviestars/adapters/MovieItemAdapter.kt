package com.opsat7373.moviestars.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.opsat7373.moviestars.R
import com.opsat7373.moviestars.data.model.Movie
import com.opsat7373.moviestars.databinding.MovieItemBinding
import java.util.*


class MovieItemAdapter : RecyclerView.Adapter<MovieItemAdapter.MovieItemViewHolder>() {
    private var moviesList : List<Movie>

    fun setList(moviesList: List<Movie>) {
        this.moviesList = moviesList
    }

    init {
        this.moviesList = LinkedList<Movie>()
    }

    class MovieItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val binding : MovieItemBinding = MovieItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.movie_item,
            parent,
            false
        )
        return MovieItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.binding.apply {
            movieName.text = moviesList[position].original_title
            movieRating.text = moviesList[position].vote_average
            Glide
                .with(holder.itemView)
                .load("https://image.tmdb.org/t/p/w500/" + moviesList[position].posterUrl)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .placeholder(R.drawable.elt8cu357vowbvtitkmldeg32fs)
                .error(R.drawable.ic_launcher_foreground)
                .into(imageView)
        }
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }
}