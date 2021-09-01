package com.flagos.photos.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.flagos.photos.domain.Hits

private const val FIRST_PAGE = 1

class PixabayPagingSource(
    private val pixabayApi: PixabayApi,
    private val query: String
) : PagingSource<Int, Hits>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hits> {
        val position = params.key ?: FIRST_PAGE

        return try {
            val response = pixabayApi.getPhotos(query, position, params.loadSize)
            val hits = response.hits
            val prevKey = if (position == FIRST_PAGE) null else position - 1
            val nextKey = if (hits.isEmpty()) null else position + 1
            LoadResult.Page(hits, prevKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Hits>): Int? = state.anchorPosition
}