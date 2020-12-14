package com.opsat7373.moviestars.data.datasource

import com.opsat7373.moviestars.data.model.MoviePoster
import com.opsat7373.moviestars.data.model.PopularMoviesList
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody

interface MovieDataSourceInterface {
    fun getPopularList() : Single<PopularMoviesList>

    fun getMoviePoster(path : String) : Single<ResponseBody>
}