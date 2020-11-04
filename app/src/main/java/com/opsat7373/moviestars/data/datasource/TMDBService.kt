package com.opsat7373.moviestars.data.datasource

import com.opsat7373.moviestars.data.model.Movie
import com.opsat7373.moviestars.data.model.PopularMoviesList
import retrofit2.Call
import retrofit2.http.GET

interface TMDBService {

    @GET("3/movie/popular?api_key=be9904bbe25ba300a25f8089c42ba44f")
    fun getPopularList() : Call<PopularMoviesList>
}