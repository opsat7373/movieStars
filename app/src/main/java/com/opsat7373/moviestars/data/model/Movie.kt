package com.opsat7373.moviestars.data.model

import android.graphics.Bitmap

data class Movie(val original_title : String, val poster_path : String) {
    var poster : Bitmap? = null
}