package com.einhesari.batmanmovies.presentation

import androidx.lifecycle.ViewModel
import com.einhesari.batmanmovies.domain.model.mapToPresetationModel
import com.einhesari.batmanmovies.domain.usecase.MoviesUseCase
import com.einhesari.batmanmovies.presentation.model.BatmanMovie
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MoviesViewModel @Inject constructor(private val moviesUseCase: MoviesUseCase) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val allMovies = mutableListOf<BatmanMovie>()
    private val state: BehaviorRelay<MoviesFragmentState> = BehaviorRelay.create()

    fun getState() = state.hide()

    fun getAllBatmanMovies() {
        state.accept(MoviesFragmentState.Loading)
        moviesUseCase.getAllMovies()
            .subscribeOn(Schedulers.io())
            .subscribe({
                it.forEach {
                    allMovies.add(it.mapToPresetationModel())
                }
                state.accept(MoviesFragmentState.GotAllMovies(allMovies))
            }, {
                state.accept(MoviesFragmentState.Error(it))
            }).let {
                compositeDisposable.add(it)
            }
    }


}

sealed class MoviesFragmentState {
    object Loading : MoviesFragmentState()
    data class Error(val error: Throwable) : MoviesFragmentState()
    data class GotAllMovies(val movies: List<BatmanMovie>) : MoviesFragmentState()
}