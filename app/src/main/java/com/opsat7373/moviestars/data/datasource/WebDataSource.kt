package com.opsat7373.moviestars.data.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.opsat7373.moviestars.data.model.Movie
import com.opsat7373.moviestars.data.model.PopularMoviesList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class WebDataSource(val client : Retrofit) : MovieDataSourceInterface {

    override fun getAll(): MutableLiveData<List<Movie>> {
        val service = client.create(TMDBService::class.java)
        val request: Call<PopularMoviesList> = service.getPopularList()
        val data :  MutableLiveData<List<Movie>> =  MutableLiveData<List<Movie>>()
        request.enqueue(object : Callback<PopularMoviesList> {
            override fun onResponse(call: Call<PopularMoviesList>?,
                           response: Response<PopularMoviesList>) {
                if (response.isSuccessful) {
                    data.value = response.body()?.results ?: LinkedList<Movie>()
                }
            }

            override fun onFailure(call: Call<PopularMoviesList>?, t: Throwable?) {
                data.value = LinkedList<Movie>()
                Log.e("remote api failure", t.toString())
            }
        })
        return data
    }
}