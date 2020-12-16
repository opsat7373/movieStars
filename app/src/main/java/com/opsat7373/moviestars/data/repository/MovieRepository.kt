package com.opsat7373.moviestars.data.repository

import com.opsat7373.moviestars.data.datasource.remote.RetrofitClient
import com.opsat7373.moviestars.data.datasource.remote.WebDataSource
import com.opsat7373.moviestars.data.model.Movie
import com.opsat7373.moviestars.domain.MovieRepositoryInterface
import io.reactivex.rxjava3.core.Single

class MovieRepository : MovieRepositoryInterface {
    private val retrofitClient = RetrofitClient().getClient()
    private val remoteMovieDataSource = WebDataSource(retrofitClient)

    override fun getPopularMoviesList(): Single<List<Movie>> {
        return remoteMovieDataSource.getPopularList()
            .toObservable()
            .flatMapIterable { movies -> movies.results }
            .toList()
    }
}