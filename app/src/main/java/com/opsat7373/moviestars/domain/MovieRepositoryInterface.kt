package com.opsat7373.moviestars.domain

import androidx.lifecycle.LiveData
import com.opsat7373.moviestars.data.model.Movie
import com.opsat7373.moviestars.data.model.PopularMoviesList
import io.reactivex.rxjava3.core.Single

interface MovieRepositoryInterface {
    fun getMoviesList() : Single<PopularMoviesList>
}