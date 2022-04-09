
package com.kin.carta.android.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kin.carta.android.api.CaseStudiesService

class CaseStudiesPagingSource(
    private val service: CaseStudiesService
) : PagingSource<Int, CaseStudy>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CaseStudy> {
        return try {
            val response = service.getCaseStudies()
            val cases = response.caseStudies
            LoadResult.Page(
                data = cases,
                prevKey = null,
                nextKey = null
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CaseStudy>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}
