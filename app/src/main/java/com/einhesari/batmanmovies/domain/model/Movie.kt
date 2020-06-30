package com.einhesari.batmanmovies.domain.model

import com.einhesari.batmanmovies.presentation.model.DetailedMovie

data class Movie(
    val title: String,
    val year: String,
    val imdbID: String,
    val type: String,
    val poster: String,
    val rated: String,
    val released: String,
    val genre: String,
    val director: String,
    val imdbRating: String,
    val imdbVotes: String
)

fun Movie.mapToPresentationModel(): DetailedMovie {
    val rating = "Rated $imdbRating out of $imdbVotes votes"
    return DetailedMovie(
        title = title,
        year = year,
        imdbID = imdbID,
        type = type,
        poster = poster,
        rated = rated,
        released = released,
        genre = genre,
        director = director,
        rating = rating
    )
}