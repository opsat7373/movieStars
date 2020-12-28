package com.opsat7373.moviestars.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.*
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.opsat7373.moviestars.R
import com.opsat7373.moviestars.presentation.viewmodel.MoviesListViewModel
import com.opsat7373.moviestars.adapters.MovieItemAdapter
import com.opsat7373.moviestars.data.model.Movie
import com.opsat7373.moviestars.databinding.FragmentMoviesListFramgentBinding
import com.opsat7373.moviestars.events.FirstMovieLoadedEvent
import com.opsat7373.moviestars.events.MovieSelectedEvent
import io.reactivex.rxjava3.observers.DisposableObserver
import java.util.*
import kotlin.reflect.cast

private lateinit var viewBinding : FragmentMoviesListFramgentBinding

/**
 * A simple [Fragment] subclass.
 * Use the [.newInstance] factory method to
 * create an instance of this fragment.
 */
class MoviesListFragment : Fragment() {

    private lateinit var moviesListViewModel : MoviesListViewModel

    private var listEndingObserver : DisposableObserver<Int>? = null

    private var isTwoPanelMode  = false

    private var detailsFragment : Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parentFragmentManager.addOnBackStackChangedListener(
            object : FragmentManager.OnBackStackChangedListener {
                override fun onBackStackChanged() {
                    for (i in 0 until parentFragmentManager.backStackEntryCount) println(
                        parentFragmentManager.getBackStackEntryAt(i).toString()
                    )
                }
            })
        moviesListViewModel = ViewModelProvider(this).get(MoviesListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentMoviesListFramgentBinding.inflate(layoutInflater)
        val viewAdapter = MovieItemAdapter()
        val linearLayoutManager = LinearLayoutManager(activity)


        viewAdapter.getOnClickObservable().subscribe {
            moviesListViewModel.movieClick(it)
        }

        viewBinding.moviesListRecyclerView.apply{
            layoutManager = linearLayoutManager
            adapter = viewAdapter
        }

        moviesListViewModel.apply{
            loadList()
            selectedMovieItem.observe(viewLifecycleOwner, {
                viewAdapter.selectedItem.set(it?.id ?: 0)
            })
            isLoading.observe(viewLifecycleOwner, { loading ->
                if (!loading) {
                    subscribeListEndingObserver()
                }
            })
            moviesList.observe(viewLifecycleOwner, {
                viewAdapter.setList(moviesListViewModel.moviesList.value ?: LinkedList<Movie>())
                viewAdapter.notifyDataSetChanged()

            })

            eventBus.subscribe{event ->
                when (event) {
                    is MovieSelectedEvent -> {
                        if (isTwoPanelMode) {
                            MovieDetailsFragment::class.cast(detailsFragment).loadMovie(event.movie.id)
                        } else {

                            val args : Bundle = Bundle()
                            args.putInt("movieId", event.movie.id)
                            parentFragmentManager.commit {
                                add<MovieDetailsFragment>(R.id.movies_list_fragment, args = args)
                                addToBackStack(null)
                            }
                        }
                    }
                    is FirstMovieLoadedEvent -> {
                        if (isTwoPanelMode) {
                            MovieDetailsFragment::class.cast(detailsFragment).loadMovie(event.movie.id)
                        }
                    }
                }
            }
        }

        detailsFragment =
            parentFragmentManager.findFragmentById(R.id.movie_details_fragment)
        isTwoPanelMode = detailsFragment != null
        viewAdapter.highlightSelected.set(isTwoPanelMode)

        return viewBinding.root
    }

    fun subscribeListEndingObserver() {
        listEndingObserver = object : DisposableObserver<Int>() {
            override fun onNext(t: Int?) {
                listEndingObserver?.dispose()
                moviesListViewModel.loadList()
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