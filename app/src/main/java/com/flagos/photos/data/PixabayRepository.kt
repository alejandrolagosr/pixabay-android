package com.flagos.photos.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import javax.inject.Inject
import javax.inject.Singleton

private const val PAGE_SIZE = 50
private const val MAX_SIZE = 100

@Singleton
class PixabayRepository @Inject constructor(private val pixabayApi: PixabayApi) {

    fun getSearchResults(query: String) = Pager(
        PagingConfig(PAGE_SIZE, MAX_SIZE, false),
        pagingSourceFactory = { PixabayPagingSource(pixabayApi, query) }
    ).liveData
}
