package com.einhesari.batmanmovies.data.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.einhesari.batmanmovies.data.model.remote.SingleMovieResponse
import com.einhesari.batmanmovies.domain.model.Movie

@Entity(tableName = "detailed_movies")
data class DetailedMovieEntity(

    @PrimaryKey
    val imdbID: String,
    val title: String,
    val year: String,
    val type: String,
    val poster: String,
    val rated: String,
    val released: String,
    val genre: String,
    val director: String,
    val imdbRating: String,
    val imdbVotes: String
)
fun DetailedMovieEntity.mapToDomainModel(): Movie {
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