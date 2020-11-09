package com.opsat7373.moviestars.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.opsat7373.moviestars.data.model.Movie
import com.opsat7373.moviestars.data.repository.MovieRepository
import java.util.*

class MoviesListViewModel : ViewModel() {
    private var _moviesList : MutableLiveData<List<Movie>> = MutableLiveData()

    public val moviesList : LiveData<List<Movie>>
    get() = _moviesList

    fun initList() : LiveData<List<Movie>> {
        _moviesList = MovieRepository().getMoviesList()
        return moviesList
    }
}