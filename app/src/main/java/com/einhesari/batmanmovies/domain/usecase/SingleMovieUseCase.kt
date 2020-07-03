package com.einhesari.batmanmovies.domain.usecase

import com.einhesari.batmanmovies.domain.model.Movie
import com.einhesari.batmanmovies.domain.repository.MovieRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class SingleMovieUseCase @Inject constructor(private val repository: MovieRepository) {


    fun getMovieFromServer(imdbID: String): Single<Movie> {
        return repository.getMovieFromServer(imdbID)
    }

    fun getMovieFromCache(imdbID: String): Single<Movie> {
        return repository.getMovieFromCache(imdbID)
    }

    fun cacheMovie(movie: Movie): Completable {
        return repository.setMovieToDb(movie)
    }
}