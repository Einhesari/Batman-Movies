package com.einhesari.batmanmovies.data.datasource.remote

import com.einhesari.batmanmovies.data.model.remote.SearchResponse
import com.einhesari.batmanmovies.data.model.remote.SingleMovieResponse
import com.einhesari.batmanmovies.domain.model.Movie
import com.einhesari.batmanmovies.domain.model.SearchedMovie
import io.reactivex.Single

interface MovieRemoteDataSource {

    fun getAllBatmanMovies(): Single<SearchResponse>

    fun getMovie(imdbID: String): Single<SingleMovieResponse>
}