/*
 * Copyright 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.sunflower.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.samples.apps.sunflower.api.CaseStudiesService

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
