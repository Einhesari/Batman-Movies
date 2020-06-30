package com.einhesari.batmanmovies.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListToDetailViewModel : ViewModel() {

    var selectedMovieId: String = ""
    var firstListPosition: Int = 0

}