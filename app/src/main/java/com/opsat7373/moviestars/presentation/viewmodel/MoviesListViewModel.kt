package com.opsat7373.moviestars.presentation.viewmodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.opsat7373.moviestars.data.model.Movie
import com.opsat7373.moviestars.data.repository.MovieRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.ResponseBody
import java.util.*

class MoviesListViewModel : ViewModel() {
    private var _moviesList: MutableLiveData<List<Movie>> = MutableLiveData()

    private var _bitmap : MutableLiveData<Bitmap> = MutableLiveData()

    val bitmap : LiveData<Bitmap>
        get() = _bitmap

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