package com.opsat7373.moviestars.domain

import androidx.lifecycle.MutableLiveData
import com.opsat7373.moviestars.data.model.Movie

interface MovieRepositoryInterface {
    fun getMoviesList() : MutableLiveData<List<Movie?>>
}