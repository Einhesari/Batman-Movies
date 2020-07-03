package com.einhesari.batmanmovies.domain.repository

import com.einhesari.batmanmovies.domain.model.Movie
import com.einhesari.batmanmovies.domain.model.SearchedMovie
import io.reactivex.Completable
import io.reactivex.Single

interface MovieRepository {

    fun getAllBatmanMoviesFromServer(): Single<List<SearchedMovie>>

    fun getAllBatmanMoviesFromCache(): Single<List<SearchedMovie>>

    fun getMovieFromServer(imdbID: String): Single<Movie>

    fun getMovieFromCache(imdbID: String): Single<Movie>

    fun setAllMoviesToDb(movies: List<SearchedMovie>): Completable

    fun setMovieToDb(movie: Movie): Completable
}