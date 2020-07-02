package com.einhesari.batmanmovies.data.repository

import com.einhesari.batmanmovies.data.datasource.database.MovieCacheDataSource
import com.einhesari.batmanmovies.data.datasource.remote.MovieRemoteDataSource
import com.einhesari.batmanmovies.data.model.database.mapToDomainModel
import com.einhesari.batmanmovies.domain.model.Movie
import com.einhesari.batmanmovies.domain.model.SearchedMovie
import com.einhesari.batmanmovies.domain.repository.MovieRepository
import io.reactivex.Single
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieCacheDataSource: MovieCacheDataSource
) : MovieRepository {

    override fun getAllBatmanMovies(): Single<List<SearchedMovie>> {
        return movieCacheDataSource.getAllBatmanMovies()
            .flatMap {
                if (it.isEmpty())
                    return@flatMap movieRemoteDataSource.getAllBatmanMovies()
                else
                    return@flatMap Single.just(it.map {
                        it.mapToDomainModel()
                    })
            }
    }

    override fun getMovie(imdbID: String): Single<Movie> {
        return movieRemoteDataSource.getMovie(imdbID)
    }
}