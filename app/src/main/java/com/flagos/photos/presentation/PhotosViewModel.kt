package com.flagos.photos.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.flagos.photos.data.PixabayRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val repository: PixabayRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val query = savedStateHandle.getLiveData(CURRENT_QUERY, DEFAULT_QUERY)

    val photos = query.switchMap { queryString ->
        repository.getSearchResults(queryString).cachedIn(viewModelScope)
    }

    companion object {
        private const val CURRENT_QUERY = "current_query"
        private const val DEFAULT_QUERY = "Mexico"
    }
}