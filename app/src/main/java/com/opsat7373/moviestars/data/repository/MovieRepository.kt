package com.opsat7373.moviestars.data.repository

import androidx.lifecycle.LiveData
import com.opsat7373.moviestars.data.datasource.WebDataSource
import com.opsat7373.moviestars.data.model.Movie
import com.opsat7373.moviestars.domain.MovieRepositoryInterface

class MovieRepository : MovieRepositoryInterface {

    val remoteDataSource = WebDataSource()

    override fun getMoviesList(): LiveData<List<Movie>> {
        return remoteDataSource.getAll()
    }


}