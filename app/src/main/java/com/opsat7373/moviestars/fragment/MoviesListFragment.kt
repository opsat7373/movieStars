package com.opsat7373.moviestars.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.opsat7373.moviestars.ViewModel.MoviesListViewModel
import com.opsat7373.moviestars.adapters.MovieItemAdapter
import com.opsat7373.moviestars.databinding.FragmentMoviesListFramgentBinding

private lateinit var viewBinding : FragmentMoviesListFramgentBinding

/**
 * A simple [Fragment] subclass.
 * Use the [.newInstance] factory method to
 * create an instance of this fragment.
 */
class MoviesListFragment : Fragment() {

    lateinit var moviesListViewModel : MoviesListViewModel

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
        moviesListViewModel.loadMoviesList()
        val viewAdapter = MovieItemAdapter(moviesListViewModel.moviesList.value)

        moviesListViewModel.moviesList.observe(viewLifecycleOwner, Observer {
            viewAdapter.setList(moviesListViewModel.moviesList.value)
            viewAdapter.notifyDataSetChanged()
        })

        viewBinding.button.setOnClickListener {
            moviesListViewModel.refreshList()
        }

        viewBinding.moviesListRecyclerView.apply{
            layoutManager = linearLayoutManager
            adapter = viewAdapter
        }
        return viewBinding.root
    }
}