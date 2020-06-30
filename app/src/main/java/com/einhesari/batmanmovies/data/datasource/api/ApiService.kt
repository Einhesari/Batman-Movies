package com.einhesari.batmanmovies.data.datasource.api

import com.einhesari.batmanmovies.data.model.SearchResponse
import com.einhesari.batmanmovies.data.model.SingleMovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/")
    fun getAllBatmanMovies(@Query("s") star: String): Single<SearchResponse>

    @GET("/")
    fun getMovieDetail(@Query("i") imdbID: String): Single<SingleMovieResponse>
}