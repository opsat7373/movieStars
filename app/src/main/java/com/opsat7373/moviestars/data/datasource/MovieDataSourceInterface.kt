package com.opsat7373.moviestars.data.datasource

import com.opsat7373.moviestars.data.model.PopularMoviesList
import io.reactivex.rxjava3.core.Single

interface MovieDataSourceInterface {
    fun getPopularList() : Single<PopularMoviesList>
}