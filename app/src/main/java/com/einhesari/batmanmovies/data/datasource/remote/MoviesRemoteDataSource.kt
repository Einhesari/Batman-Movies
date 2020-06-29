package com.einhesari.batmanmovies.data.datasource.remote

import com.einhesari.batmanmovies.data.model.SingleMovieResponse
import com.einhesari.batmanmovies.domain.model.Movie
import com.einhesari.batmanmovies.domain.model.SearchedMovie
import io.reactivex.Single

interface MoviesRemoteDataSource {

    fun getAllBatmanMovies(): Single<List<SearchedMovie>>

    fun getMovie(imdbID: String): Single<Movie>
}