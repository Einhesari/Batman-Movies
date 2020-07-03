package com.einhesari.batmanmovies.domain.repository

import com.einhesari.batmanmovies.data.model.database.DetailedMovieEntity
import com.einhesari.batmanmovies.domain.model.Movie
import com.einhesari.batmanmovies.domain.model.SearchedMovie
import com.einhesari.batmanmovies.presentation.model.DetailedMovie
import io.reactivex.Completable
import io.reactivex.Single

interface MovieRepository {

    fun getAllBatmanMovies(): Single<Pair<Boolean, List<SearchedMovie>>>

    fun getMovie(imdbID: String): Single<Pair<Boolean, Movie>>

    fun setAllMoviesToDb(movies: List<SearchedMovie>): Completable

    fun setMovieToDb(movie: Movie): Completable
}