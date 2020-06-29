package com.einhesari.batmanmovies.domain.usecase

import com.einhesari.batmanmovies.domain.model.Movie
import com.einhesari.batmanmovies.domain.repository.MovieRepository
import io.reactivex.Single
import javax.inject.Inject

class MovieUseCase @Inject constructor(private val repository: MovieRepository) {

    fun getMovie(imdbId: String): Single<Movie> {
        return repository.getMovie(imdbId)
    }
}