package com.einhesari.batmanmovies.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListToDetailViewModel : ViewModel() {

    var selectedMovieId: String = ""
    var lastListPosition: Int = 0

}