package com.opsat7373.moviestars.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.opsat7373.moviestars.data.model.Movie
import com.opsat7373.moviestars.data.repository.MovieRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*

class MoviesListViewModel : ViewModel() {

    private var page : Int = 1

    private var _moviesList: MutableLiveData<List<Movie>> = MutableLiveData(LinkedList<Movie>())

    private var _isLoading : MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)

    val moviesList: LiveData<List<Movie>>
        get() = _moviesList

    val isLoading : LiveData<Boolean>
        get() = _isLoading

    fun loadList(): LiveData<List<Movie>> {
        _isLoading.value = true
        MovieRepository().getPopularMoviesList(page++)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe { result ->
                _moviesList.value = _moviesList.value?.plus(result)
                _isLoading.value = false
        }
        return moviesList
    }
}