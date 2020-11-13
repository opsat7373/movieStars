package com.opsat7373.moviestars.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.opsat7373.moviestars.data.datasource.RetrofitClient
import com.opsat7373.moviestars.data.datasource.WebDataSource
import com.opsat7373.moviestars.data.model.Movie
import com.opsat7373.moviestars.data.model.PopularMoviesList
import com.opsat7373.moviestars.domain.MovieRepositoryInterface
import io.reactivex.rxjava3.core.Single

class MovieRepository : MovieRepositoryInterface {
    val retrofitClient = RetrofitClient().getClient()
    val remoteMovieDataSource = WebDataSource(retrofitClient)

    override fun getMoviesList(): Single<PopularMoviesList> {
        return remoteMovieDataSource.getMoviesService().getPopularList()
    }


}