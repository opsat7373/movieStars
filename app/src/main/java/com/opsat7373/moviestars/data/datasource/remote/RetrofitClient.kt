package com.opsat7373.moviestars.data.datasource.remote

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    private val retrofitClient : Retrofit = Retrofit.Builder()
            .baseUrl(TMDB_API_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getClient (): Retrofit {
        return retrofitClient
    }
}