package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapter.MovieListAdapter
import com.example.myapplication.model.MovieData
import com.example.myapplication.viewModels.MovieListViewModel

class MovieListFragment : Fragment() {

    private lateinit var movieListRV: RecyclerView
    private lateinit var viewModel: MovieListViewModel
    private lateinit var movieListAdapter: MovieListAdapter
    private lateinit var movieData: MovieData
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)
        setUpUI(view)
        setupViewModel()
        setupObserver()
        return view
    }

    private fun setupObserver() {
        try {
            viewModel.movieList.observe(requireActivity()) {
                progressBar.visibility = View.GONE
                if (it.isSuccessful) {
                    movieData = it.body()!!
                    movieListRV.adapter = MovieListAdapter(movieData.results, movieData)
                } else {
                    Toast.makeText(requireActivity(), "API Not Working", Toast.LENGTH_LONG).show()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this)[MovieListViewModel::class.java]
        try {
            viewModel.getMovieList()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setUpUI(view: View) {
        movieListRV = view.findViewById(R.id.movieListRV)
        progressBar = view.findViewById(R.id.progressBar)
        movieListRV.layoutManager = LinearLayoutManager(requireActivity())
        movieListAdapter = MovieListAdapter(arrayListOf())
    }
}