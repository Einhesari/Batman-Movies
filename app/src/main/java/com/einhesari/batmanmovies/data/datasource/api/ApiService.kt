package com.einhesari.batmanmovies.data.datasource.api

import com.einhesari.batmanmovies.data.model.SearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET
    fun getAllBatmanMovies(@Query("s") star: String): Single<SearchResponse>

//    fun getMovieDetail(@Query("i") imdbID: String): Single<Movie>
}