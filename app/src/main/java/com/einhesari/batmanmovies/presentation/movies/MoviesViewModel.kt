package com.einhesari.batmanmovies.presentation.movies

import androidx.lifecycle.ViewModel
import com.einhesari.batmanmovies.domain.model.SearchedMovie
import com.einhesari.batmanmovies.domain.model.mapToPresetationModel
import com.einhesari.batmanmovies.domain.usecase.AllMoviesUseCase
import com.einhesari.batmanmovies.presentation.model.BatmanMovie
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MoviesViewModel @Inject constructor(val useCase: AllMoviesUseCase) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val allMovies = mutableListOf<BatmanMovie>()
    private val state: BehaviorRelay<MoviesFragmentState> = BehaviorRelay.create()

    fun getState() = state.hide()

    fun getAllBatmanMovies() {
        state.accept(MoviesFragmentState.Loading)
        useCase.getAllMovies()
            .subscribeOn(Schedulers.io())
            .subscribe({
                it.second.forEach {
                    allMovies.add(it.mapToPresetationModel())
                }
                state.accept(MoviesFragmentState.GotAllMovies(allMovies))
                checkAndUpdateCacheIfNecessary(it)
            }, {
                state.accept(MoviesFragmentState.Error(it))
            }).let {
                compositeDisposable.add(it)
            }
    }

    private fun checkAndUpdateCacheIfNecessary(result: Pair<Boolean, List<SearchedMovie>>) {
        if (result.first.not()) {
            useCase.cacheAllMovies(result.second).subscribeOn(Schedulers.io())
                .subscribe()
                .let {
                    compositeDisposable.add(it)
                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}

sealed class MoviesFragmentState {
    object Loading : MoviesFragmentState()
    data class Error(val error: Throwable) : MoviesFragmentState()
    data class GotAllMovies(val movies: List<BatmanMovie>) : MoviesFragmentState()
}