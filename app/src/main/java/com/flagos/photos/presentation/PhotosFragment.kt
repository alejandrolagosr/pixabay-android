package com.flagos.photos.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.paging.LoadState.Error
import androidx.paging.LoadState.Loading
import androidx.paging.LoadState.NotLoading
import com.flagos.photos.databinding.FragmentPhotosBinding
import com.flagos.photos.presentation.adapter.PhotosAdapter
import dagger.hilt.android.AndroidEntryPoint

private const val ONE_ITEM = 1

@AndroidEntryPoint
class PhotosFragment : Fragment() {

    private val viewModel by viewModels<PhotosViewModel>()

    private lateinit var photosAdapter: PhotosAdapter

    private var _binding: FragmentPhotosBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentPhotosBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapter()
        setUpViews()
        initObservers()
    }

    private fun setUpAdapter() {
        photosAdapter = PhotosAdapter()
        photosAdapter.addLoadStateListener { state ->
            with(binding) {
                progressBar.isVisible = state.source.refresh is Loading
                recycler.isVisible = state.source.refresh is NotLoading
                buttonRetry.isVisible = state.source.refresh is Error
                textViewError.isVisible = state.source.refresh is Error

                if (state.source.refresh is NotLoading && state.append.endOfPaginationReached && photosAdapter.itemCount < ONE_ITEM) {
                    recycler.isVisible = false
                    textViewEmpty.isVisible = true
                } else {
                    textViewEmpty.isVisible = false
                }
            }
        }
    }

    private fun setUpViews() {
        with(binding) {
            buttonRetry.setOnClickListener { photosAdapter.retry() }
            recycler.apply { adapter = photosAdapter }
        }
    }

    private fun initObservers() {
        viewModel.photos.observe(viewLifecycleOwner) {
            photosAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
