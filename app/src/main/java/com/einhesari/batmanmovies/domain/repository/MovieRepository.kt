package com.einhesari.batmanmovies.domain.repository

import com.einhesari.batmanmovies.domain.model.SearchedMovie
import io.reactivex.Single

interface MovieRepository {

    fun getAllBatmanMovies(): Single<List<SearchedMovie>>

//    fun getMovie(): Single<Movie>
}