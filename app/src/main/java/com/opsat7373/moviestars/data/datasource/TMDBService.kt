package com.opsat7373.moviestars.data.datasource

import com.opsat7373.moviestars.data.model.PopularMoviesList
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface TMDBService {

    @GET("3/movie/popular?api_key=be9904bbe25ba300a25f8089c42ba44f")
    fun getPopularList() : Single<PopularMoviesList>
}