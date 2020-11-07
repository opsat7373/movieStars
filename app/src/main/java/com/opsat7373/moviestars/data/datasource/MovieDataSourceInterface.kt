package com.opsat7373.moviestars.data.datasource

import androidx.lifecycle.LiveData
import com.opsat7373.moviestars.data.model.Movie

interface MovieDataSourceInterface {
    fun getAll() : LiveData<List<Movie>>

}