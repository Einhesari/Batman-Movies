package com.einhesari.batmanmovies.data.repository

import com.einhesari.batmanmovies.data.datasource.database.MovieCacheDataSource
import com.einhesari.batmanmovies.data.datasource.remote.MovieRemoteDataSource
import com.einhesari.batmanmovies.data.model.database.mapToDomainModel
import com.einhesari.batmanmovies.data.model.remote.FoundedMovie
import com.einhesari.batmanmovies.data.model.remote.mapToDbModel
import com.einhesari.batmanmovies.data.model.remote.mapToDomainModel
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
                if (it.isEmpty()) {
                    getMoviesFromServer()
                } else {
                    return@flatMap Single.just(it.map {
                        it.mapToDomainModel()
                    })
                }


            }
    }

    override fun getMovie(imdbID: String): Single<Movie> {
        return movieRemoteDataSource.getMovie(imdbID)
    }

    private fun getMoviesFromServer(): Single<List<SearchedMovie>> {
        return movieRemoteDataSource.getAllBatmanMovies().map {
            setMoviesToDb(it.foundedMovies)
            return@map it.foundedMovies.map {
                it.mapToDomainModel()
            }
        }
    }

    private fun setMoviesToDb(movies: List<FoundedMovie>) {
        movieCacheDataSource.setAllBatmanMovies(movies.map { it.mapToDbModel() })
    }
}