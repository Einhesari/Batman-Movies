package com.einhesari.batmanmovies.presenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.einhesari.batmanmovies.R
import com.einhesari.batmanmovies.ViewModelProviderFactory
import com.einhesari.batmanmovies.component
import com.einhesari.batmanmovies.databinding.FragmentMoviesBinding
import javax.inject.Inject

class MoviesFragment : Fragment() {

    lateinit var binding: FragmentMoviesBinding

    @Inject
    lateinit var factory: ViewModelProviderFactory
    lateinit var viewModel: MoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        component
            .viewModelProviderFactory
            .create()
            .inject(this)
        viewModel = ViewModelProvider(this, factory)[MoviesViewModel::class.java]
        viewModel.getAllBatmanMovies()

    }
}