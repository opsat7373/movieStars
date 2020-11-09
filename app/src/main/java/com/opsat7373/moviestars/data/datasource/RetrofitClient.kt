package com.opsat7373.moviestars.data.datasource

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    private val retrofitClient : Retrofit

    init {
        retrofitClient = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun getClient (): Retrofit {
        return retrofitClient
    }
}