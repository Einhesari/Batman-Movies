package com.einhesari.batmanmovies.domain.model

import com.einhesari.batmanmovies.data.model.database.MovieEntity
import com.einhesari.batmanmovies.presentation.model.BatmanMovie

data class SearchedMovie(
    val title: String,
    val year: String,
    val imdbID: String,
    val type: String,
    val poster: String
)

fun SearchedMovie.mapToPresetationModel(): BatmanMovie {
    return BatmanMovie(
        title = title,
        year = year,
        imdbID = imdbID,
        type = type,
        poster = poster
    )
}

fun SearchedMovie.mapToDbModel(): MovieEntity {
    return MovieEntity(
        title = title,
        year = year,
        imdbID = imdbID,
        type = type,
        poster = poster
    )
}