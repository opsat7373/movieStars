package com.opsat7373.moviestars.presentation.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModelProvider
import com.opsat7373.moviestars.databinding.FragmentMovieDetailsBinding
import com.opsat7373.moviestars.presentation.viewmodel.MovieDetailsViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [MovieDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieDetailsFragment : Fragment() {

    private lateinit var movieDetailsViewModel : MovieDetailsViewModel

    private val isLoading : ObservableBoolean = ObservableBoolean(true)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieDetailsViewModel = ViewModelProvider(this).get(MovieDetailsViewModel::class.java)

        val movieId : Int? = arguments?.getInt("movieId")
        if (movieId != null) {
            movieDetailsViewModel.loadMovie(movieId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val dataBinding = FragmentMovieDetailsBinding.inflate(layoutInflater)

        dataBinding.movieItem = movieDetailsViewModel.movieItem
        dataBinding.lifecycleOwner = viewLifecycleOwner
        dataBinding.isLoading = isLoading
        movieDetailsViewModel.isLoading.observe(viewLifecycleOwner, {
            isLoading.set(it)
            Log.i("MovieDetails", "change isLoading ${it}")
        })

        return dataBinding.root
    }

    fun loadMovie(movieId : Int) {
        movieDetailsViewModel.loadMovie(movieId)
    }
}