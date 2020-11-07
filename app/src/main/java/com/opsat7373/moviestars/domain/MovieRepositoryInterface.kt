package com.opsat7373.moviestars.domain

import androidx.lifecycle.LiveData
import com.opsat7373.moviestars.data.model.Movie

interface MovieRepositoryInterface {
    fun getMoviesList() : LiveData<List<Movie>>
}