package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.myapplication.R
import com.example.myapplication.model.Result
import com.example.myapplication.network.RetrofitBuilder
import com.squareup.picasso.Picasso

class MovieDetailFragment : Fragment() {

    private val args by navArgs<MovieDetailFragmentArgs>()
    private lateinit var movieData: Result

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_movie_detail, container, false)
        movieData = args.result
        setValues(view)
        return view
    }

    private fun setValues(view: View) {
        val posterImage = view.findViewById<ImageView>(R.id.poster_image)
        val releaseDate = view.findViewById<TextView>(R.id.release_date_text_value)
        val ratingValue = view.findViewById<TextView>(R.id.rating_text_value)
        val popularityValue = view.findViewById<TextView>(R.id.popularity_text_value)
        val overviewValue = view.findViewById<TextView>(R.id.overview_text_value)

        Picasso.get().load(RetrofitBuilder.POSTER_BASE_URL + movieData.poster_path)
            .into(posterImage)
        releaseDate.text = movieData.release_date
        ratingValue.text = movieData.vote_count.toString()
        popularityValue.text = movieData.popularity.toString()
        overviewValue.text = movieData.overview
    }
}