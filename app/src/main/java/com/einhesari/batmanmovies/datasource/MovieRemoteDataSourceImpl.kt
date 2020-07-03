package com.einhesari.batmanmovies.datasource

import com.einhesari.batmanmovies.data.datasource.api.ApiService
import com.einhesari.batmanmovies.data.datasource.remote.MovieRemoteDataSource
import com.einhesari.batmanmovies.data.model.remote.SearchResponse
import com.einhesari.batmanmovies.data.model.remote.SingleMovieResponse
import com.einhesari.batmanmovies.data.model.remote.mapToDomainModel
import com.einhesari.batmanmovies.domain.model.Movie
import com.einhesari.batmanmovies.domain.model.SearchedMovie
import io.reactivex.Single
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(private val apiService: ApiService) :
    MovieRemoteDataSource {
    private val movieStar = "batman"
    override fun getAllBatmanMovies(): Single<SearchResponse> {
        return apiService.getAllBatmanMovies(movieStar)
    }

    override fun getMovie(imdbID: String): Single<SingleMovieResponse> {
        return apiService.getMovieDetail(imdbID)
    }
}