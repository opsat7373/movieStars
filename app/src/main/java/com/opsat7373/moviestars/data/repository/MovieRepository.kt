package com.opsat7373.moviestars.data.repository

import android.graphics.BitmapFactory
import com.opsat7373.moviestars.data.datasource.remote.RetrofitClient
import com.opsat7373.moviestars.data.datasource.remote.WebDataSource
import com.opsat7373.moviestars.data.model.Movie
import com.opsat7373.moviestars.domain.MovieRepositoryInterface
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody

class MovieRepository : MovieRepositoryInterface {
    val retrofitClient = RetrofitClient().getClient()
    val imageRetrofitClient = RetrofitClient().getImageClient()
    val remoteMovieDataSource = WebDataSource(retrofitClient, imageRetrofitClient)


    override fun getPopularMoviesList(): Single<List<Movie>> {
        return remoteMovieDataSource.getPopularList()
            .toObservable()
            .flatMapIterable { movies -> movies.results }
            .flatMapSingle { movie ->
                val path =
                    if (movie.poster_path[0] == '/') movie.poster_path.substring(1) else movie.poster_path
                remoteMovieDataSource.getMoviePoster(path)
                    .map { posterResponse ->
                        movie.apply {
                            poster = BitmapFactory.decodeStream(posterResponse.byteStream())
                        }
                    }
            }.toList()
    }
}