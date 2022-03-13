package com.example.myapplication.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.MovieData
import com.example.myapplication.network.RetrofitBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieListViewModel : ViewModel() {
    val movieList = MutableLiveData<Response<MovieData>>()

    fun getMovieList() {
        viewModelScope.launch(Dispatchers.IO) {
            val movieItems = RetrofitBuilder.getClient().getMovieList(1)
            movieList.postValue(movieItems)
        }
    }
}