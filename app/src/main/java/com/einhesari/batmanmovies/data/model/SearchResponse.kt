package com.einhesari.batmanmovies.data.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("search") val foundedMovies: List<FoundedMovie>,
    @SerializedName("totalResults") val totalResults: String,
    @SerializedName("Response") val Response: String
)

data class FoundedMovie(
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("imdbID") val imdbID: String,
    @SerializedName("Type") val type: String,
    @SerializedName("Poster") val poster: String
)