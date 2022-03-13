package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.MovieData
import com.example.myapplication.model.Result
import com.example.myapplication.network.RetrofitBuilder.POSTER_BASE_URL
import com.example.myapplication.ui.MovieListFragmentDirections
import com.squareup.picasso.Picasso

class MovieListAdapter(var movieList: List<Result>, var movieData: MovieData? = null) :
    RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val singleMovie = movieList[position]

        holder.title.text = singleMovie.title
        holder.overview.text = singleMovie.overview

        Picasso.get().load(POSTER_BASE_URL + singleMovie.backdrop_path).into(holder.image)

        holder.itemView.setOnClickListener {
            val action =
                MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(singleMovie)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //Could have used viewBinding,but due to time constraints using in simple way.
        var image: ImageView = itemView.findViewById(R.id.poster_image)
        var title: TextView = itemView.findViewById(R.id.title_textview)
        var overview: TextView = itemView.findViewById(R.id.overview_text)
    }
}