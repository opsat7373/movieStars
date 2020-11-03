package com.opsat7373.moviestars.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.opsat7373.moviestars.data.model.Movie

class MoviesListViewModel : ViewModel() {
    val moviesList = MutableLiveData<Array<Movie?>>()

    fun loadMoviesList() {
        moviesList.value = Movie.getMoviesList()
    }

    fun refreshList() {
        moviesList.value = Movie.getAnotherList()
    }
}