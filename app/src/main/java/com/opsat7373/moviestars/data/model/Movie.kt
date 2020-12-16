package com.opsat7373.moviestars.data.model

data class Movie(
    val original_title : String,
    val poster_path : String,
    val vote_average : String) {
    val posterUrl : String
        get() = if (poster_path[0] == '/') poster_path.substring(1) else poster_path
}