package com.opsat7373.moviestars.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.opsat7373.moviestars.R
import com.opsat7373.moviestars.databinding.MovieItemBinding
import com.opsat7373.moviestars.model.Movie

class MovieItemAdapter(moviesList : Array<Movie?>?) : RecyclerView.Adapter<MovieItemAdapter.MovieItemViewHolder>() {
    var moviesList : Array<Movie?>

    fun setList(moviesList : Array<Movie?>?) {
        this.moviesList = moviesList ?: arrayOfNulls(0)
    }

    init {
        this.moviesList = moviesList ?: arrayOfNulls(0)
    }

    class MovieItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val binding : MovieItemBinding = MovieItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.binding.movieName.text = moviesList[position]?.movieName.toString()
        println("position : ${position.toString()}")
    }

    override fun getItemCount(): Int {
        println(moviesList.size)
        return moviesList.size
    }
}