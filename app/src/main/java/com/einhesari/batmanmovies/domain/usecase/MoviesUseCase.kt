package com.einhesari.batmanmovies.domain.usecase

import com.einhesari.batmanmovies.domain.model.SearchedMovie
import com.einhesari.batmanmovies.domain.repository.MovieRepository
import io.reactivex.Single
import javax.inject.Inject

class MoviesUseCase @Inject constructor(val repository: MovieRepository) {

    fun getAllMovies(): Single<List<SearchedMovie>> {
        return repository.getAllBatmanMovies()
    }
}