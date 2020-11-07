package com.opsat7373.moviestars.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.opsat7373.moviestars.data.model.Movie
import com.opsat7373.moviestars.data.repository.MovieRepository
import java.util.*

class MoviesListViewModel : ViewModel() {
    private var moviesList : LiveData<List<Movie>> = MutableLiveData()

    fun initList() : List<Movie> {
        moviesList = MovieRepository().getMoviesList()
        return this.getList()
    }

    fun getList() : List<Movie> {
        return moviesList.value ?: LinkedList()
    }

    fun getLiveData() : LiveData<List<Movie>> {
        return moviesList
    }
}