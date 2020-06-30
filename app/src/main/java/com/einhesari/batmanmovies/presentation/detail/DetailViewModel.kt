package com.einhesari.batmanmovies.presentation.detail

import androidx.lifecycle.ViewModel
import com.einhesari.batmanmovies.domain.model.mapToPresentationModel
import com.einhesari.batmanmovies.domain.usecase.MovieUseCase
import com.einhesari.batmanmovies.presentation.model.DetailedMovie
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailViewModel @Inject constructor(val useCase: MovieUseCase) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val state: BehaviorRelay<DetailFragmentState> = BehaviorRelay.create()
    fun getState() = state.hide()

    fun getMovie(imdbId: String) {
        state.accept(DetailFragmentState.Loading)
        useCase.getMovie(imdbId)
            .subscribeOn(Schedulers.io())
            .subscribe({
                state.accept(DetailFragmentState.GotMovie(it.mapToPresentationModel()))
            }, {
                state.accept(DetailFragmentState.Error(it))
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