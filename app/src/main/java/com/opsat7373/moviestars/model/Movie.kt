package com.opsat7373.moviestars.model
private val moviesList : Array<String> =  arrayOf("Die hard 1" , "2012", "Star wars")
private val moviesList2 : Array<String> =  arrayOf("Die hard 2" , "2012 2", "Star wars 2")

data class Movie(val movieName : String) {
    companion object {
        fun getMoviesList() : Array<Movie?> {
            val moviesArray : Array<Movie?> = arrayOfNulls(moviesList.size)
            var index = 0;
            for (movieName in moviesList) {
                moviesArray[index++] = Movie(movieName)
            }
            return moviesArray
        }

        fun getAnotherList() : Array<Movie?> {
            val moviesArray : Array<Movie?> = arrayOfNulls(moviesList2.size)
            var index = 0;
            for (movieName in moviesList2) {
                moviesArray[index++] = Movie(movieName)
            }
            return moviesArray
        }
    }
}