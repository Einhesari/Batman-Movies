package com.einhesari.batmanmovies.domain.usecase

import com.einhesari.batmanmovies.domain.model.Movie
import com.einhesari.batmanmovies.domain.repository.MovieRepository
import com.einhesari.batmanmovies.presentation.model.DetailedMovie
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class SingleMovieUseCase @Inject constructor(private val repository: MovieRepository) {


    fun getMovie(imdbID: String): Single<Pair<Boolean, Movie>> {
        return repository.getMovie(imdbID)
    }

    fun cacheMovie(movie: Movie): Completable {
        return repository.setMovieToDb(movie)
    }
}