package com.opsat7373.moviestars.data.repository

import androidx.lifecycle.MutableLiveData
import com.opsat7373.moviestars.data.datasource.WebDataSource
import com.opsat7373.moviestars.data.model.Movie
import com.opsat7373.moviestars.domain.MovieRepositoryInterface

class MovieRepository : MovieRepositoryInterface {

    val remoteDataSource = WebDataSource()

    override fun getMoviesList(): MutableLiveData<List<Movie?>> {
        return remoteDataSource.getAll()
    }


}