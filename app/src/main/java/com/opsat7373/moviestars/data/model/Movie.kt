package com.opsat7373.moviestars.data.model

data class Movie(
    val id : Int,
    val original_title : String,
    val poster_path : String,
    val vote_average : String,
    val vote_count : Int,
    val overview : String,
    val runtime : Int) {
    var posterUrl : String? = null
}