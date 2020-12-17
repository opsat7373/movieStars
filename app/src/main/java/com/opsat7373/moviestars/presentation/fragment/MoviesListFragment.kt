package com.opsat7373.moviestars.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.opsat7373.moviestars.presentation.viewmodel.MoviesListViewModel
import com.opsat7373.moviestars.adapters.MovieItemAdapter
import com.opsat7373.moviestars.data.model.Movie
import com.opsat7373.moviestars.databinding.FragmentMoviesListFramgentBinding
import java.util.*

private lateinit var viewBinding : FragmentMoviesListFramgentBinding

/**
 * A simple [Fragment] subclass.
 * Use the [.newInstance] factory method to
 * create an instance of this fragment.
 */
class MoviesListFragment : Fragment() {

    private lateinit var moviesListViewModel : MoviesListViewModel

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
        moviesListViewModel.initList()
        val viewAdapter = MovieItemAdapter()
        moviesListViewModel.moviesList.observe(viewLifecycleOwner, {
            viewAdapter.setList(moviesListViewModel.moviesList.value ?: LinkedList<Movie>())
            viewAdapter.notifyDataSetChanged()
        })

        viewBinding.moviesListRecyclerView.apply{
            layoutManager = linearLayoutManager
            adapter = viewAdapter
        }
        return viewBinding.root
    }
}