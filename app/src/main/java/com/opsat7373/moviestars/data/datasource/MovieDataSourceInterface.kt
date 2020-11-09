package com.opsat7373.moviestars.data.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.opsat7373.moviestars.data.model.Movie

interface MovieDataSourceInterface {
    fun getAll() : MutableLiveData<List<Movie>>

}