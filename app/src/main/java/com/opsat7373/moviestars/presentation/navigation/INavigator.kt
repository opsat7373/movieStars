package com.opsat7373.moviestars.presentation.navigation

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.opsat7373.moviestars.R
import com.opsat7373.moviestars.presentation.fragment.MovieDetailsFragment

interface INavigator {
    fun navigateToMovieDetails(movieId: Int)
}

class Navigator(private val fragmentManager: FragmentManager) : INavigator {
    override fun navigateToMovieDetails(movieId: Int) {
        var detailsFragment : MovieDetailsFragment? = null
        detailsFragment =
            fragmentManager.findFragmentById(R.id.movie_details_fragment) as MovieDetailsFragment?
        val isTwoPanelMode = detailsFragment != null
        if (isTwoPanelMode) {
            detailsFragment?.loadMovie(movieId)
        } else {
            val args = Bundle()
            args.putInt("movieId", movieId)
            fragmentManager.commit {
                add<MovieDetailsFragment>(R.id.movies_list_fragment, args = args)
                addToBackStack(null)
            }
        }
    }
}

