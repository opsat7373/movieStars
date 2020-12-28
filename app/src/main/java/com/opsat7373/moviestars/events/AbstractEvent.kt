package com.opsat7373.moviestars.events

import com.opsat7373.moviestars.data.model.Movie

sealed class AbstractEvent {
}

class MovieSelectedEvent(item : Movie) : AbstractEvent() {
    val movie : Movie = item

}

class FirstMovieLoadedEvent(item : Movie) : AbstractEvent() {
    val movie : Movie = item

}