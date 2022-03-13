package com.example.myapplication.network

import com.example.myapplication.model.MovieData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {

    @GET("movie/now_playing")
    suspend fun getMovieList(@Query("page") page: Int) : Response<MovieData>
}