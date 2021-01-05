package com.opsat7373.moviestars.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.opsat7373.moviestars.data.model.Movie
import com.opsat7373.moviestars.data.repository.MovieRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieDetailsViewModel : ViewModel() {

    private val _movieItem : MutableLiveData<Movie?> = MutableLiveData<Movie?>()

    private var _isLoading : MutableLiveData<Boolean> = MutableLiveData<Boolean>(true)

    val movieItem : LiveData<Movie?> = _movieItem
    val isLoading : LiveData<Boolean> = _isLoading

    fun loadMovie(movieId : Int): LiveData<Movie?> {
        _isLoading.value = true
        MovieRepository().getMovie(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).
            subscribe { result ->
                _movieItem.value = result
                _isLoading.value = false
            }
        return movieItem
    }


}