package com.opsat7373.moviestars.presentation.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.opsat7373.moviestars.presentation.viewmodel.MoviesListViewModel
import com.opsat7373.moviestars.adapters.MovieItemAdapter
import com.opsat7373.moviestars.data.model.Movie
import com.opsat7373.moviestars.databinding.FragmentMoviesListFramgentBinding
import io.reactivex.rxjava3.observers.DisposableObserver
import java.util.*

private lateinit var viewBinding : FragmentMoviesListFramgentBinding

/**
 * A simple [Fragment] subclass.
 * Use the [.newInstance] factory method to
 * create an instance of this fragment.
 */
class MoviesListFragment : Fragment() {

    private lateinit var moviesListViewModel : MoviesListViewModel

    private var page : Int = 1

    private val isLoading : ObservableBoolean = ObservableBoolean(false)

    private var listEndingObserver : DisposableObserver<Int>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moviesListViewModel = ViewModelProvider(this).get(MoviesListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentMoviesListFramgentBinding.inflate(layoutInflater)

        val linearLayoutManager = LinearLayoutManager(activity)
        moviesListViewModel.loadList()
        val viewAdapter = MovieItemAdapter()

        viewBinding.isLoading = isLoading

        viewBinding.moviesListRecyclerView.apply{
            layoutManager = linearLayoutManager
            adapter = viewAdapter
        }

        moviesListViewModel.isLoading.observe(viewLifecycleOwner, { loading ->
            isLoading.set(loading)
            if (!loading) {
                subscribeListEndingObserver()
            }
        })

        moviesListViewModel.moviesList.observe(viewLifecycleOwner, {
            viewAdapter.setList(moviesListViewModel.moviesList.value ?: LinkedList<Movie>())
            viewAdapter.notifyDataSetChanged()

        })
        return viewBinding.root
    }

    fun subscribeListEndingObserver() {
        listEndingObserver = object : DisposableObserver<Int>() {
            override fun onNext(t: Int?) {
                listEndingObserver?.dispose()
                moviesListViewModel.loadList(page++)
            }

            override fun onError(e: Throwable?) {
                Log.e("Recycler view ", e.toString())
            }

            override fun onComplete() {
                Log.i("completed", "completed")
            }

        }
        viewBinding.moviesListRecyclerView.getListEndingStream().subscribe(listEndingObserver)
    }
}