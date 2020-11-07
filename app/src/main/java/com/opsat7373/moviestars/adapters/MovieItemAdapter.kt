package com.opsat7373.moviestars.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.opsat7373.moviestars.R
import com.opsat7373.moviestars.databinding.MovieItemBinding
import com.opsat7373.moviestars.data.model.Movie
import java.util.*

class MovieItemAdapter() : RecyclerView.Adapter<MovieItemAdapter.MovieItemViewHolder>() {
    var moviesList : List<Movie>

    fun setList(moviesList : List<Movie>) {
        this.moviesList = moviesList
    }

    init {
        this.moviesList = LinkedList<Movie>()
    }

    class MovieItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val binding : MovieItemBinding = MovieItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.binding.movieName.text = moviesList[position].original_title
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }
}