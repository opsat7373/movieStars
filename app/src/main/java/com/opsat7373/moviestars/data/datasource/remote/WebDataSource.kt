package com.opsat7373.moviestars.data.datasource.remote

import com.opsat7373.moviestars.data.datasource.MovieDataSourceInterface
import com.opsat7373.moviestars.data.model.MoviePoster
import com.opsat7373.moviestars.data.model.PopularMoviesList
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody
import retrofit2.Retrofit

class WebDataSource(val client : Retrofit, val imageClient : Retrofit) : MovieDataSourceInterface {

    override fun getPopularList(): Single<PopularMoviesList> {
        return client.create(TMDBService::class.java).getMoviesList()
    }
}