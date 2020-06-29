package com.einhesari.batmanmovies.datasource

import com.einhesari.batmanmovies.data.datasource.api.ApiService
import com.einhesari.batmanmovies.data.datasource.remote.MoviesRemoteDataSource
import com.einhesari.batmanmovies.data.model.mapToDomainModel
import com.einhesari.batmanmovies.domain.model.SearchedMovie
import io.reactivex.Single
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(private val apiService: ApiService) :
    MoviesRemoteDataSource {
    private val movieStar = "batman"
    val allMovies = mutableListOf<SearchedMovie>()
    override fun getAllBatmanMovies(): Single<List<SearchedMovie>> {
        return apiService.getAllBatmanMovies(movieStar)
            .flatMap {
                it.foundedMovies.forEach {
                    allMovies.add(it.mapToDomainModel())
                }
                return@flatMap Single.just(allMovies)
            }

    }
}