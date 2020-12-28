package com.opsat7373.moviestars.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.opsat7373.moviestars.data.model.Movie
import com.opsat7373.moviestars.data.repository.MovieRepository
import com.opsat7373.moviestars.events.AbstractEvent
import com.opsat7373.moviestars.events.FirstMovieLoadedEvent
import com.opsat7373.moviestars.events.MovieSelectedEvent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.*

class MoviesListViewModel : ViewModel() {

    private var page : Int = 1

    private var _moviesList: MutableLiveData<List<Movie>> = MutableLiveData(LinkedList<Movie>())

    private var _isLoading : MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)

    private var _selectedMovieItem : MutableLiveData<Movie?> = MutableLiveData<Movie?>(null)

    var eventBus : PublishSubject<AbstractEvent> = PublishSubject.create()

    val moviesList: LiveData<List<Movie>>
        get() = _moviesList

    val isLoading : LiveData<Boolean>
        get() = _isLoading

    val selectedMovieItem : LiveData<Movie?>
        get() = _selectedMovieItem

    fun loadList(): LiveData<List<Movie>> {
        _isLoading.value = true
        MovieRepository().getPopularMoviesList(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe { result ->
                _moviesList.value = _moviesList.value?.plus(result)
                _isLoading.value = false
                if (page == 1 && result.size > 0) {
                    val firstMovie = result.get(0)
                    _selectedMovieItem.value = firstMovie
                    eventBus.onNext(FirstMovieLoadedEvent(firstMovie))
                }
        }
        return moviesList
    }

    fun movieClick(movie: Movie) {
        _selectedMovieItem.value = movie
        eventBus.onNext(MovieSelectedEvent(movie))
    }
}