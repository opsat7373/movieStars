package com.opsat7373.moviestars.data.model

import com.google.gson.annotations.SerializedName

data class Movie(
    val id : Int,
    @SerializedName("original_title")
    val originalTitle : String,
    @SerializedName("poster_path")
    val posterPath : String,
    @SerializedName("vote_average")
    val voteAverage : String,
    @SerializedName("vote_count")
    val voteCount : Int,
    val overview : String,
    val runtime : Int) {
    var posterUrl : String? = null
}