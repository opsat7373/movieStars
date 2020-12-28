package com.opsat7373.moviestars.data.repository

import com.opsat7373.moviestars.data.datasource.remote.RetrofitClient
import com.opsat7373.moviestars.data.datasource.remote.TMDB_IMAGE_API_POSTER_URL
import com.opsat7373.moviestars.data.datasource.remote.WebDataSource
import com.opsat7373.moviestars.data.model.Movie
import com.opsat7373.moviestars.domain.MovieRepositoryInterface
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.Consumer
import java.util.concurrent.TimeUnit

class MovieRepository : MovieRepositoryInterface {
    private val retrofitClient = RetrofitClient().getClient()
    private val remoteMovieDataSource = WebDataSource(retrofitClient)

    override fun getPopularMoviesList(page : Int): Single<List<Movie>> {
        return remoteMovieDataSource.getPopularList(page)
            .toObservable()
            .delay(1000, TimeUnit.MILLISECONDS)
            .flatMapIterable { movies -> movies.results }
            .map{movie ->
                movie.apply{
                    posterUrl =  "${TMDB_IMAGE_API_POSTER_URL}${movie.poster_path}"
                }
            }
            .toList()
    }

    override fun getMovie(movieId: Int): Single<Movie> {
        return remoteMovieDataSource.getMovie(movieId)
            .doOnSuccess { movie ->
                movie.apply {
                    posterUrl = "${TMDB_IMAGE_API_POSTER_URL}${movie.poster_path}"
                }
            }
            .delay(1000, TimeUnit.MILLISECONDS)
    }
}