package com.opsat7373.moviestars.data.datasource.remote

import com.opsat7373.moviestars.data.model.PopularMoviesList
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBService {

    @GET("3/movie/popular?api_key=be9904bbe25ba300a25f8089c42ba44f")
    fun getMoviesList(@Query("page") page : Int = 1) : Single<PopularMoviesList>
}