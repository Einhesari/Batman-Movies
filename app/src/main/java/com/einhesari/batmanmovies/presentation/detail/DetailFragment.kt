package com.einhesari.batmanmovies.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.einhesari.batmanmovies.R
import com.einhesari.batmanmovies.ViewModelProviderFactory
import com.einhesari.batmanmovies.component
import com.einhesari.batmanmovies.databinding.FragmentDetailBinding
import com.einhesari.batmanmovies.presentation.ListToDetailViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class DetailFragment : Fragment() {

    private val selectionViewModel: ListToDetailViewModel by activityViewModels()
    private lateinit var viewModel: DetailViewModel

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var binding: FragmentDetailBinding
    private lateinit var compositeDisposable: CompositeDisposable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        component
            .viewModelProviderFactory
            .create()
            .inject(this)

        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        compositeDisposable = CompositeDisposable()

        initDataInteraction(savedInstanceState)
    }

    private fun initDataInteraction(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            viewModel.getMovie(selectionViewModel.selectedMovieId)
        }
        viewModel.getState()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                handleState(it)
            }.let {
                compositeDisposable.add(it)
            }
    }

    private fun handleState(state: DetailFragmentState) {
        when (state) {
            is DetailFragmentState.GotMovie -> {
                binding.loading.hide()
                binding.movie = state.movie
                binding.genreTv.isSelected = true
                binding.movieTitleTv.isSelected = true
            }
            is DetailFragmentState.Error -> {
                Toast.makeText(context, getString(R.string.detail_error), Toast.LENGTH_LONG).show()
                findNavController().navigateUp()
            }
            is DetailFragmentState.Loading -> {
                binding.loading.show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.dispose()

    }
}