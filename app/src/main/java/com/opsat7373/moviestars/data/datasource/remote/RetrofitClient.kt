package com.opsat7373.moviestars.data.datasource.remote

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    private val retrofitClient : Retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val imageRetrofitClient : Retrofit = Retrofit.Builder()
        .baseUrl("https://image.tmdb.org/")
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    fun getClient (): Retrofit {
        return retrofitClient
    }

    fun getImageClient (): Retrofit {
        return imageRetrofitClient
    }
}