package com.einhesari.batmanmovies.data.model.remote

import com.einhesari.batmanmovies.data.model.database.MovieEntity
import com.einhesari.batmanmovies.domain.model.SearchedMovie
import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("Search") val foundedMovies: List<FoundedMovie>,
    @SerializedName("totalResults") val totalResults: String,
    @SerializedName("Response") val Response: String
)

data class FoundedMovie(
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("imdbID") val imdbID: String,
    @SerializedName("Type") val type: String,
    @SerializedName("Poster") val posterUrl: String
)

fun FoundedMovie.mapToDomainModel(): SearchedMovie {
    return SearchedMovie(
        title = title,
        year = year,
        imdbID = imdbID,
        type = type,
        poster = posterUrl
    )
}