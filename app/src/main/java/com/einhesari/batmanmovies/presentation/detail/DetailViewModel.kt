package com.einhesari.batmanmovies.presentation.detail

import androidx.lifecycle.ViewModel
import com.einhesari.batmanmovies.domain.usecase.MovieUseCase
import com.einhesari.batmanmovies.presentation.MoviesFragmentState
import com.einhesari.batmanmovies.presentation.model.DetailedMovie
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DetailViewModel @Inject constructor(val useCase: MovieUseCase) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val state: BehaviorRelay<MoviesFragmentState> = BehaviorRelay.create()
    fun getState() = state.hide()

    fun getMovie(imdbId: String) {
        useCase.getMovie(imdbId)
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({

            }, {

            }).let {
                compositeDisposable.add(it)
            }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}

sealed class DetailFragmentState {
    object Loading : DetailFragmentState()
    data class Error(val error: Throwable) : DetailFragmentState()
    data class GotMovie(val movie: DetailedMovie) : DetailFragmentState()
}