package com.opsat7373.moviestars.domain

import com.opsat7373.moviestars.data.model.Movie
import io.reactivex.rxjava3.core.Single

interface MovieRepositoryInterface {
    fun getPopularMoviesList(page : Int) : Single<List<Movie>>
    fun getMovie(movieId : Int) : Single<Movie>
}