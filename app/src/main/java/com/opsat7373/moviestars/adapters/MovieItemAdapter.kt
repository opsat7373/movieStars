package com.opsat7373.moviestars.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.opsat7373.moviestars.R
import com.opsat7373.moviestars.data.model.Movie
import com.opsat7373.moviestars.databinding.MovieItemBinding
import java.util.*

const val MOVIE_ITEM_TYPE = 1
const val LOADER_ITEM_TYPE = 2

class MovieItemAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var moviesList : LinkedList<Movie?>

    fun setList(moviesList: List<Movie>) {
        this.moviesList  = LinkedList<Movie?>(moviesList)
        this.moviesList.add(null)
    }

    class MovieItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val binding : MovieItemBinding = MovieItemBinding.bind(itemView)
    }

    class ProgressBar(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            MOVIE_ITEM_TYPE -> {
                val itemView = LayoutInflater.from(parent.context).inflate(
                    R.layout.movie_item,
                    parent,
                    false
                )
                return MovieItemViewHolder(itemView)

            }
            else -> {
                val itemView = LayoutInflater.from(parent.context).inflate(
                    R.layout.movie_item_progress_bar,
                    parent,
                    false
                )
                return ProgressBar(itemView)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieItemViewHolder -> {
                holder.binding.movieItem = moviesList[position]
                holder.binding.position = position
            }
        }
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun getItemViewType(position: Int): Int {
        if (moviesList[position] == null) {
            return LOADER_ITEM_TYPE
        }
        return MOVIE_ITEM_TYPE
    }
}