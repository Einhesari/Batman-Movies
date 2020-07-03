package com.einhesari.batmanmovies.domain.usecase

import com.einhesari.batmanmovies.domain.model.Movie
import com.einhesari.batmanmovies.domain.model.SearchedMovie
import com.einhesari.batmanmovies.domain.repository.MovieRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class AllMoviesUseCase @Inject constructor(private val repository: MovieRepository) {

    fun getAllMoviesFromServer(): Single<List<SearchedMovie>> {
        return repository.getAllBatmanMoviesFromServer()
    }

    fun getAllMoviesFromCache(): Single<List<SearchedMovie>> {
        return repository.getAllBatmanMoviesFromCache()
    }

    fun getMovie(imdbId: String): Single<Pair<Boolean, Movie>> {
        return repository.getMovie(imdbId)
    }

    fun cacheAllMovies(movies: List<SearchedMovie>): Completable {
        return repository.setAllMoviesToDb(movies)
    }
}