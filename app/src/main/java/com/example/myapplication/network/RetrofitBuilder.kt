package com.example.myapplication.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {

    // This should be a part of gradle properties for security, keeping is here due to time constraints.
    private const val API_KEY = "6e63c2317fbe963d76c3bdc2b785f6d1"
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342"


    private val requestInterceptor = Interceptor { chain ->
        val url = chain.request()
            .url()
            .newBuilder()
            .addQueryParameter("api_key", API_KEY)
            .build()

        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()

        return@Interceptor chain.proceed(request)
    }

    var okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(requestInterceptor)
        .readTimeout(60, TimeUnit.SECONDS)
        .build()

    // Could have used hilt, but due to time issues using in simple way.
    fun getClient(): APIInterface {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(APIInterface::class.java)
    }
}