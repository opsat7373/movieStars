package com.opsat7373.moviestars.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.opsat7373.moviestars.data.model.Movie
import com.opsat7373.moviestars.data.repository.MovieRepository

class MoviesListViewModel : ViewModel() {
    var moviesList = MutableLiveData<List<Movie?>>()

    fun loadMoviesList() {
        moviesList = MovieRepository().getMoviesList()
    }

    fun refreshList() {
        moviesList  = MovieRepository().getMoviesList()
    }
}