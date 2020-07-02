package com.einhesari.batmanmovies.data.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.einhesari.batmanmovies.data.model.remote.FoundedMovie
import com.einhesari.batmanmovies.domain.model.SearchedMovie
import com.einhesari.batmanmovies.presentation.model.BatmanMovie


@Entity(tableName = "batman_movies")
data class MovieEntity(
    @PrimaryKey
    val imdbID: String,
    val title: String,
    val year: String,
    val type: String,
    val poster: String
)

fun MovieEntity.mapToDomainModel(): SearchedMovie {
    return SearchedMovie(
        title = title,
        year = year,
        imdbID = imdbID,
        type = type,
        poster = poster
    )
}