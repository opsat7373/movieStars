package com.opsat7373.moviestars.data.datasource.remote

import com.opsat7373.moviestars.data.model.MoviePoster
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface TMDBImageService {

    @GET("t/p/w500/{posterPath}")
    fun getMoviePoster(@Path("posterPath")posterPath : String) : Single<ResponseBody>
}
