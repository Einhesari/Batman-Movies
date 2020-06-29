package com.einhesari.batmanmovies.data.repository

import com.einhesari.batmanmovies.data.datasource.remote.MoviesRemoteDataSource
import com.einhesari.batmanmovies.domain.model.SearchedMovie
import com.einhesari.batmanmovies.domain.repository.MovieRepository
import io.reactivex.Single
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val movieRemoteDataSource: MoviesRemoteDataSource) :
    MovieRepository {
    override fun getAllBatmanMovies(): Single<List<SearchedMovie>> {
        return movieRemoteDataSource.getAllBatmanMovies()
    }
}