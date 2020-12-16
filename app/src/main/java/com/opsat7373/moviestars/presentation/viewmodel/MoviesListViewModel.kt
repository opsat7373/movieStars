package com.opsat7373.moviestars.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.opsat7373.moviestars.data.model.Movie
import com.opsat7373.moviestars.data.repository.MovieRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MoviesListViewModel : ViewModel() {
    private var _moviesList: MutableLiveData<List<Movie>> = MutableLiveData()
    val moviesList: LiveData<List<Movie>>
        get() = _moviesList

    fun initList(): LiveData<List<Movie>> {
        MovieRepository().getPopularMoviesList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe { result ->
            _moviesList.value = result
        }
        return moviesList
    }
}