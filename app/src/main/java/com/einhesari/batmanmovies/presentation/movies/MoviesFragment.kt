package com.einhesari.batmanmovies.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.einhesari.batmanmovies.R
import com.einhesari.batmanmovies.ViewModelProviderFactory
import com.einhesari.batmanmovies.component
import com.einhesari.batmanmovies.databinding.FragmentMoviesBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding
    private val compositeDisposable = CompositeDisposable()
    private val selectionViewModel: ListToDetailViewModel by activityViewModels()

    @Inject
    lateinit var factory: ViewModelProviderFactory
    private lateinit var viewModel: MoviesViewModel

    private lateinit var adapter: MovieAdapter
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var movieRv: RecyclerView
    private var firstVisibleMovie = 0
    private val stateBundlePositionKey = "POSITION_KEY"

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

        binding.host = this

        initViewInteraction(savedInstanceState)
        initDataInteraction(savedInstanceState)

    }

    private fun initDataInteraction(savedInstanceState: Bundle?) {
        if (savedInstanceState == null && selectionViewModel.selectedMovieId.equals("")) {
            viewModel.getAllBatmanMovies()
        }
        viewModel.getState()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                handleState(it)
            }.let {
                compositeDisposable.add(it)
            }
    }

    private fun initViewInteraction(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            firstVisibleMovie = it.getInt(stateBundlePositionKey)
        }
        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = MovieAdapter()
        adapter.selectedMovie()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                firstVisibleMovie = getFirstVisibleItemPosition()
                selectionViewModel.firstListPosition = firstVisibleMovie
                selectionViewModel.selectedMovieId = it.imdbID
                findNavController().navigate(R.id.action_moviesFragment_to_detailFragment)
            }.let {
                compositeDisposable.add(it)
            }
        movieRv = binding.movieRv
        gridLayoutManager = GridLayoutManager(context, 2)
        movieRv.layoutManager = gridLayoutManager
        movieRv.adapter = adapter

    }

    private fun handleState(state: MoviesFragmentState) {
        when (state) {
            is MoviesFragmentState.GotAllMovies -> {
                binding.loading.hide()
                adapter.submitList(state.movies)
                binding.networkErrorGroup.visibility = View.GONE
                movieRv.scrollToPosition(firstVisibleMovie)
            }
            is MoviesFragmentState.Error -> {
                binding.loading.hide()
                binding.networkErrorGroup.visibility = View.VISIBLE
            }
            is MoviesFragmentState.Loading -> {
                binding.loading.show()
                binding.networkErrorGroup.visibility = View.GONE
            }
        }
    }


    private fun getFirstVisibleItemPosition(): Int {
        (movieRv.layoutManager as? GridLayoutManager)
            ?.let {
                return it.findFirstCompletelyVisibleItemPosition()
            }
        return 0
    }

    fun tryAgain(view: View) {
        viewModel.getAllBatmanMovies()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.dispose()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(stateBundlePositionKey, firstVisibleMovie)
        super.onSaveInstanceState(outState)
    }
}