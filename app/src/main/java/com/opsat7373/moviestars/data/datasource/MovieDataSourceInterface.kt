package com.opsat7373.moviestars.data.datasource

import com.opsat7373.moviestars.data.model.Movie
import com.opsat7373.moviestars.data.model.PopularMoviesList
import io.reactivex.rxjava3.core.Single

interface MovieDataSourceInterface {
    fun getPopularList(page : Int) : Single<PopularMoviesList>
    fun getMovie(movieId: Int): Single<Movie>
}