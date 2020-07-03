package com.einhesari.batmanmovies.data.repository

import com.einhesari.batmanmovies.data.datasource.database.MovieCacheDataSource
import com.einhesari.batmanmovies.data.datasource.remote.MovieRemoteDataSource
import com.einhesari.batmanmovies.data.model.database.mapToDomainModel
import com.einhesari.batmanmovies.data.model.remote.mapToDomainModel
import com.einhesari.batmanmovies.domain.model.Movie
import com.einhesari.batmanmovies.domain.model.SearchedMovie
import com.einhesari.batmanmovies.domain.model.mapToDbModel
import com.einhesari.batmanmovies.domain.repository.MovieRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieCacheDataSource: MovieCacheDataSource
) : MovieRepository {


    override fun getAllBatmanMoviesFromServer(): Single<List<SearchedMovie>> {
        return movieRemoteDataSource.getAllBatmanMovies().map {
            it.foundedMovies.map { it.mapToDomainModel() }
        }
//        return movieCacheDataSource.getAllBatmanMovies()
//            .flatMap {
//                if (it.isEmpty()) {
//                    movieRemoteDataSource.getAllBatmanMovies().map {
//                        Pair(false, it.foundedMovies.map { it.mapToDomainModel() })
//                    }
//                } else {
//                    Single.just(Pair(true, it.map { it.mapToDomainModel() }))
//                }
//
//            }
    }

    override fun getAllBatmanMoviesFromCache(): Single<List<SearchedMovie>> {
        return movieCacheDataSource.getAllBatmanMovies().map {
            it.map { it.mapToDomainModel() }
        }
    }

    override fun getMovie(imdbID: String): Single<Pair<Boolean, Movie>> {
        return movieCacheDataSource.getDetailedMovie(imdbID)
            .flatMap {
                if (it.isEmpty()) {
                    movieRemoteDataSource.getMovie(imdbID).map {
                        Pair(false, it)
                    }
                } else {
                    Single.just(Pair(true, it.first().mapToDomainModel()))
                }
            }
    }

    override fun setAllMoviesToDb(movies: List<SearchedMovie>): Completable {
        return movieCacheDataSource.setAllBatmanMovies(movies.map { it.mapToDbModel() })

    }

    override fun setMovieToDb(movie: Movie): Completable {
        return movieCacheDataSource.setDetailedMovie(movie.mapToDbModel())
    }

}