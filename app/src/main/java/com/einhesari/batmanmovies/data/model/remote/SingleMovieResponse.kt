package com.einhesari.batmanmovies.data.model.remote

import com.einhesari.batmanmovies.domain.model.Movie
import com.google.gson.annotations.SerializedName

data class SingleMovieResponse(
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("Rated") val rated: String,
    @SerializedName("Released") val released: String,
    @SerializedName("Runtime") val runtime: String,
    @SerializedName("Genre") val genre: String,
    @SerializedName("Director") val director: String,
    @SerializedName("Writer") val writer: String,
    @SerializedName("Actors") val actors: String,
    @SerializedName("Plot") val plot: String,
    @SerializedName("Language") val language: String,
    @SerializedName("Awards") val awards: String,
    @SerializedName("Poster") val poster: String,
    @SerializedName("Ratings") val ratings: List<Rating>,
    @SerializedName("Metascore") val metascore: String,
    @SerializedName("imdbRating") val imdbRating: String,
    @SerializedName("imdbVotes") val imdbVotes: String,
    @SerializedName("imdbID") val imdbID: String,
    @SerializedName("Type") val type: String,
    @SerializedName("DVD") val dvdRealeseData: String,
    @SerializedName("BoxOffice") val boxOffice: String,
    @SerializedName("Production") val production: String,
    @SerializedName("Website") val website: String,
    @SerializedName("Response") val response: String
)

data class Rating(
    @SerializedName("Source") val source: String,
    @SerializedName("Value") val value: String

)

fun SingleMovieResponse.mapToDomainModel(): Movie {
    return Movie(
        title = title,
        year = year,
        imdbID = imdbID,
        type = type,
        poster = poster,
        rated = rated,
        released = released,
        genre = genre,
        director = director,
        imdbRating = imdbRating,
        imdbVotes = imdbVotes
    )
}