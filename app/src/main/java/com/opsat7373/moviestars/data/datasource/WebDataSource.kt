package com.opsat7373.moviestars.data.datasource

import retrofit2.Retrofit

class WebDataSource(val client : Retrofit) : MovieDataSourceInterface {

    override fun getMoviesService(): TMDBService {
        return client.create(TMDBService::class.java)
    }
}