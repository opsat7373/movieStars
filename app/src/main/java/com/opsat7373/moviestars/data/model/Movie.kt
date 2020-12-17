package com.opsat7373.moviestars.data.model

data class Movie(
    val original_title : String,
    val poster_path : String,
    val vote_average : String) {
    var posterUrl : String? = null
}