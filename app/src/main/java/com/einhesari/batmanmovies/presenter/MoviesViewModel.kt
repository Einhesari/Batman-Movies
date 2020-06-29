package com.einhesari.batmanmovies.presenter

import android.util.Log
import androidx.lifecycle.ViewModel
import com.einhesari.batmanmovies.domain.usecase.MoviesUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MoviesViewModel @Inject constructor(private val moviesUseCase: MoviesUseCase) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun getAllBatmanMovies() {
        moviesUseCase.getAllMovies()
            .subscribeOn(Schedulers.io())
            .subscribe({
            }, {
            }).let {
                compositeDisposable.add(it)
            }
    }


}