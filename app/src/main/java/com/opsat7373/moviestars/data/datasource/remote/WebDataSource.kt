package com.opsat7373.moviestars.data.datasource.remote

import com.opsat7373.moviestars.data.datasource.MovieDataSourceInterface
import com.opsat7373.moviestars.data.model.PopularMoviesList
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit

class WebDataSource(private val client : Retrofit) : MovieDataSourceInterface {

    override fun getPopularList(page : Int): Single<PopularMoviesList> {
        return client.create(TMDBService::class.java).getMoviesList(page)
    }
}