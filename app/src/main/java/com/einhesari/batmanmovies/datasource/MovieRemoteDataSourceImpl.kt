package com.einhesari.batmanmovies.datasource

import com.einhesari.batmanmovies.data.datasource.api.ApiService
import com.einhesari.batmanmovies.data.datasource.remote.MovieRemoteDataSource
import com.einhesari.batmanmovies.data.model.mapToDomainModel
import com.einhesari.batmanmovies.domain.model.Movie
import com.einhesari.batmanmovies.domain.model.SearchedMovie
import io.reactivex.Single
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(private val apiService: ApiService) :
    MovieRemoteDataSource {
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

    override fun getMovie(imdbID: String): Single<Movie> {
        return apiService.getMovieDetail(imdbID)
            .flatMap {
                return@flatMap Single.just(it.mapToDomainModel())
            }
    }
}